package com.example.rickandmorty.presentation.characters

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.usecases.character.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : ViewModel() {

    var getAllCharacters = getCharactersUseCase()

    private val _characterListState: MutableState<CharacterListState> = mutableStateOf(
        CharacterListState()
    )
    private val handler = CoroutineExceptionHandler { _, exception ->
        _characterListState.value.isLoading = false
        _characterListState.value = CharacterListState(
            errorMessage = exception.message!!
        )
    }
    val characterListState: State<CharacterListState>
        get() = _characterListState

    init {
        getCharacters()
    }

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _gender = MutableStateFlow("")
    val gender = _gender.asStateFlow()

    private val _status = MutableStateFlow("")
    val status = _status.asStateFlow()

    fun getCharacters() = viewModelScope.launch(handler) {
        val response =
            getCharactersUseCase(_searchText.value, _status.value, _gender.value).cachedIn(
                viewModelScope
            )

        _characterListState.value = CharacterListState(
            dataList = response
        )
    }

    fun getCharactersWithFilters(
        name: String? = _searchText.value,
        status: String? = _status.value,
        gender: String? = _gender.value
    ) = viewModelScope.launch(handler) {
        val response =
            getCharactersUseCase(
                name = name,
                status = status,
                gender = gender
            ).cachedIn(
                viewModelScope
            )

        _characterListState.value = CharacterListState(
            dataList = response
        )
        Log.d("name80", "$name")
        getAllCharacters = getCharactersUseCase(name, status, gender)

    }


    private var _isSearching = MutableStateFlow(false)
    var isSearching = _isSearching.asStateFlow()
    fun onToogleSearch() {
        _isSearching.value = !_isSearching.value
        if (!_isSearching.value) {
            onSearchTextChange("")
        }
    }

    fun makeIsSearchingFalse() {
        _isSearching.value = false
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        getAllCharacters = getCharactersUseCase(_searchText.value)
    }

    fun refresh() {
        _searchText.value = ""
        _status.value = ""
        _gender.value = ""
        getAllCharacters = getCharactersUseCase(_searchText.value, _status.value, _gender.value)
    }

    fun onStatusTextChange(text: String) {
        _status.value = text
    }

    fun onGenderTextChange(text: String) {
        _gender.value = text
    }

}