package com.example.rickandmorty.presentation.location_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.model.character.ItemCharacter
import com.example.rickandmorty.domain.usecases.character.GetCharacterDetailsUseCase
import com.example.rickandmorty.domain.usecases.location.GetLocationDetailsUseCase
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
class LocationDetailsViewModel @Inject constructor(
    private val getLocationDetailsUseCase: GetLocationDetailsUseCase,
    private val getCharacterForDetailUseCase: GetCharacterDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _locationState = MutableStateFlow<LocationDetailsState>(LocationDetailsState())
    val locationState: StateFlow<LocationDetailsState> get() = _locationState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val locationId = savedStateHandle.get<Int>("locationId")

            locationId?.let {
                getLocation(it)
            }
        }
    }

    private fun getLocation(locationId: Int) {
        viewModelScope.launch {
            getLocationDetailsUseCase(id = locationId)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _locationState.update { it.copy(isLoading = true) }
                        }

                        is Resource.Success -> {
                            _locationState.update { state ->
                                state.copy(
                                    data = it.data,
                                    isLoading = false
                                )
                            }
                            it.data?.let { data -> getCharacter(data.residents) }
                        }

                        is Resource.Error -> {
                            _locationState.update { it.copy(isLoading = false, isError = true) }
                        }
                    }
                }
        }
    }

    private fun getCharacter(characters: List<String>) {
        _locationState.update { it.copy(isLoading = true) }

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
            _locationState.update { it.copy(isLoading = false, characterList = list) }
        }
    }
    fun onClickRetry() {
        val locationId = savedStateHandle.get<Int>("locationId")
        _locationState.update { it.copy(isError = false) }
        locationId?.let {
            getLocation(locationId = it)
        }
    }
}