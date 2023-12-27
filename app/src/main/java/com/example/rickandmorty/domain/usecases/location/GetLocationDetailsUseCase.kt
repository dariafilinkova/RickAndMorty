package com.example.rickandmorty.domain.usecases.location

import com.example.rickandmorty.domain.repositories.LocationDetailsRepository
import javax.inject.Inject

class GetLocationDetailsUseCase @Inject constructor(
    private val locationDetailsRepository: LocationDetailsRepository
) {
    operator fun invoke(id: Int) = locationDetailsRepository.getLocationDetails(id)
}