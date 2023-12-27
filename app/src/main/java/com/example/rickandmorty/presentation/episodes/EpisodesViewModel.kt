package com.example.rickandmorty.presentation.episodes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.usecases.episode.GetEpisodeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    val getEpisodesUseCase: GetEpisodeListUseCase
) : ViewModel() {

    val episodes = getEpisodesUseCase
    var getAllEpisodes = getEpisodesUseCase()

    private val _episodeListState: MutableState<EpisodeListState> = mutableStateOf(
        EpisodeListState()
    )

    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private var _isSearching = MutableStateFlow(false)
    var isSearching = _isSearching.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        _episodeListState.value.isLoading = false
        _episodeListState.value = EpisodeListState(
            errorMessage = exception.message!!
        )
    }

    val episodeListState: State<EpisodeListState>
        get() = _episodeListState

    init {
        getEpisodes()
    }

    fun getEpisodes() = viewModelScope.launch(handler) {

        val response = getEpisodesUseCase().cachedIn(viewModelScope)
        _episodeListState.value = EpisodeListState(
            data = response
        )
    }

    fun getEpisodesWithFilters(
    ) = viewModelScope.launch(handler) {
        var name = ""
        var episode = ""
        when (filter.value) {
            "name" -> name = _searchText.value
            "episode" -> episode = _searchText.value
            else -> {
                name = _searchText.value
                episode = _searchText.value
            }
        }

        val response =
            getEpisodesUseCase(
                name = name,
                episode = episode,
            ).cachedIn(
                viewModelScope
            )
        _episodeListState.value = EpisodeListState(
            data = response
        )
        getAllEpisodes = getEpisodesUseCase(name, episode)
    }

    fun onFilterTextChange(text: String) {
        _filter.value = text
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        getAllEpisodes = getEpisodesUseCase(_searchText.value)
    }

    fun makeIsSearchingFalse() {
        _isSearching.value = false
    }

    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    fun refresh() {
        _searchText.value = ""
        _filter.value = ""
        getAllEpisodes = getEpisodesUseCase(_searchText.value, _filter.value)
    }
}