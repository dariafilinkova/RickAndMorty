package com.example.rickandmorty.domain.usecases.location

import com.example.rickandmorty.domain.repositories.LocationsRepository
import javax.inject.Inject

class GetLocationForDetailUseCase @Inject constructor(
    private val locationForDetailRepository: LocationsRepository
) {
    suspend operator fun invoke(id: String) = locationForDetailRepository.getLocationForDetail(id)
}