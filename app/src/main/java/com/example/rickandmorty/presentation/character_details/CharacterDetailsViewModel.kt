package com.example.rickandmorty.presentation.character_details

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.usecases.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.GetEpisodesUseCase
import com.example.rickandmorty.presentation.episodes.episodes.EpisodesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val getEpisodesUseCase: GetEpisodesUseCase,
) : ViewModel() {
    private val _detailsState = mutableStateOf(CharacterDetailsState())
    val detailsState = _detailsState
    private val characterId = mutableIntStateOf(0)
    private val episodeIdFromList: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())

    init {
        val id = savedStateHandle.get<Int>("characterId")
        Log.d("characterId", "$id")
        _detailsState.value = CharacterDetailsState(isLoading = true)
        if (id != null) {
            characterId.value = id
            getCharacterById()
        }
    }

    @ExperimentalCoroutinesApi
    val episodesList = episodeIdFromList.filter { list ->
        Log.d("episodes44", "$list")
        list.isEmpty().not()
    }.flatMapLatest { idFromList ->
        val id = idFromList.joinToString()
        flow {
            getEpisodesUseCase(id).fold(
                onSuccess = {
                    Log.d("episodes", "$it")
                    emit(

                        EpisodesState(
                            data = it
                        )
                    )
                },
                onFailure = {
                    Log.d("myflow", it.localizedMessage)
                    emit(
                        EpisodesState(
                            errorMessage = it.localizedMessage ?: "Something Went Wrong"
                        )
                    )
                }
            )
        }
    }

    private fun getCharacterById() = viewModelScope.launch {

        val result = getCharacterDetailsUseCase(characterId.intValue)

        result.onSuccess { details ->
            _detailsState.value = CharacterDetailsState(data = details)
            episodeIdFromList.value = details.episode
        }
        result.onFailure {
                error ->
            _detailsState.value = CharacterDetailsState(errorMessage = error.message)
        }
    }
}
