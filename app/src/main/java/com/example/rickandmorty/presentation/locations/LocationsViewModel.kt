package com.example.rickandmorty.presentation.locations

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty.domain.usecases.location.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _locationListState: MutableState<LocationListState> = mutableStateOf(
        LocationListState()
    )
    private val _filter = MutableStateFlow("")
    val filter = _filter.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private var _isSearching = MutableStateFlow(false)
    var isSearching = _isSearching.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        _locationListState.value.isLoading = false
        _locationListState.value = LocationListState(
            errorMessage = exception.message!!
        )
    }

    var getALlLocations = getLocationsUseCase()
    val locationListState: State<LocationListState>
        get() = _locationListState

    init {
        getLocations()
    }

    fun getLocations() = viewModelScope.launch(handler) {

        val response = getLocationsUseCase().cachedIn(viewModelScope)
        _locationListState.value = LocationListState(
            dataList = response
        )
    }

    fun getLocationsWithFilters(
    ) = viewModelScope.launch(handler) {
        var name = ""
        var type = ""
        var dimension = ""
        when (filter.value) {
            "name" -> name = _searchText.value
            "type" -> type = _searchText.value
            "dimension" -> dimension = _searchText.value
            else -> {
                name = _searchText.value
                type = _searchText.value
                dimension = _searchText.value
            }
        }
        val response =
            getLocationsUseCase(
                name = name,
                type = type,
                dimension = dimension
            ).cachedIn(
                viewModelScope
            )
        _locationListState.value = LocationListState(
            dataList = response
        )
        getALlLocations = getLocationsUseCase(
            name = name,
            type = type,
            dimension = dimension
        )
    }

    fun onFilterTextChange(text: String) {
        _filter.value = text
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        getALlLocations = getLocationsUseCase(name = _searchText.value)
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
        getLocations()
    }

}