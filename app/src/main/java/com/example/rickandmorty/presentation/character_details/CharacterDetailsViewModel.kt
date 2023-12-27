package com.example.rickandmorty.presentation.character_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.episode.Episode
import com.example.rickandmorty.domain.usecases.character.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.episode.GetEpisodesUseCase
import com.example.rickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getEpisodesUseCase: GetEpisodesUseCase
) : ViewModel() {

    private val _detailsState = mutableStateOf(CharacterDetailsState())
    val detailsState = _detailsState
    private val _characterDetailState =
        MutableStateFlow<CharacterDetailsState>(CharacterDetailsState())
    val characterDetailState: StateFlow<CharacterDetailsState> get() = _characterDetailState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val characterId = savedStateHandle.get<Int>("characterId")
            characterId?.let {
                getCharacter(characterId = it)
            }
        }
    }

    fun onClickRetry() {
        val characterId = savedStateHandle.get<Int>("characterId")
        _characterDetailState.update { it.copy(isError = false) }
        characterId?.let {
            getCharacter(characterId = it)
        }
    }

    private fun getCharacter(characterId: Int) {
        viewModelScope.launch {
            getCharacterDetailsUseCase(id = characterId)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _characterDetailState.update { it.copy(isLoading = true) }
                        }

                        is Resource.Success -> {
                            _characterDetailState.update { state ->
                                state.copy(
                                    isLoading = false,
                                    data = it.data
                                )
                            }
                            if (it.data != null) getEpisode(it.data.episode)
                        }

                        is Resource.Error -> {
                            _characterDetailState.update {
                                it.copy(
                                    isError = true,
                                    isLoading = false
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun getEpisode(episodes: List<String>) {
        _characterDetailState.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            val list: MutableList<Episode> = mutableListOf()

            episodes.forEach { episodeUrl ->
                val episodeId = (episodeUrl.split("/"))[5]
                val episode = getEpisodesUseCase(episodeId = episodeId.toInt())
                list.add(episode)
            }
            _characterDetailState.update { it.copy(isLoading = false, episodes = list) }
        }
    }
}
