package com.example.rickandmorty.presentation.episode_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.usecases.character.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.episode.GetEpisodeDetailsUseCase
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.toItemCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val getEpisodeDetailsUseCase: GetEpisodeDetailsUseCase,
    private val getCharacterForDetailUseCase: GetCharacterDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _episodeState = MutableStateFlow<EpisodeDetailState>(EpisodeDetailState())
    val episodeState: StateFlow<EpisodeDetailState> get() = _episodeState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val episodeId = savedStateHandle.get<Int>("episodeId")
            episodeId?.let {
                getEpisode(it)
            }
        }
    }

    private fun getEpisode(episodeId: Int) {
        viewModelScope.launch {
            getEpisodeDetailsUseCase(id = episodeId)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _episodeState.update { it.copy(isLoading = true) }
                        }

                        is Resource.Success -> {
                            _episodeState.update { state ->
                                state.copy(
                                    data = it.data,
                                    isLoading = false
                                )
                            }
                            it.data?.let { data -> getCharacter(data.characters) }
                        }

                        is Resource.Error -> {
                            _episodeState.update { it.copy(isLoading = false, isError = true) }
                        }
                    }
                }
        }

    }

    private fun getCharacter(characters: List<String>) {
        _episodeState.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO) {
            val list: MutableList<ItemCharacter> = mutableListOf()
            characters.forEach { characterUrl ->
                val characterId = characterUrl.split("/")[5]
                val characterFlow =
                    getCharacterForDetailUseCase(id = characterId.toInt())
                characterFlow.collect {
                    it.data?.let { data -> list.add(data.toItemCharacter()) }
                }
            }
            _episodeState.update { it.copy(isLoading = false, characters = list) }
        }
    }

    fun onClickRetry() {
        val episodeId = savedStateHandle.get<Int>("episodeId")
        _episodeState.update { it.copy(isError = false) }
        episodeId?.let {
            getEpisode(episodeId = it)
        }
    }
}

