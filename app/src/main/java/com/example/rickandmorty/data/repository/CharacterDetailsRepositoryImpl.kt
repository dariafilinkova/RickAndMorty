package com.example.rickandmorty.data.repository

import com.example.rickandmorty.domain.model.CharacterDetails
import com.example.rickandmorty.domain.repositories.CharacterDetailsRepository
import com.example.rickandmorty.network.IRemoteCharactersRepository
import com.example.rickandmorty.utils.toItemCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(
    private val remoteRepository: IRemoteCharactersRepository
) : CharacterDetailsRepository, BaseRepository() {


    //    suspend fun <T> safeApiCall(
//        apiCall: suspend () -> T
//    ): Result<T> {
//        return withContext(Dispatchers.IO) {
//            try {
//                Result.success(apiCall.invoke())
//            } catch (throwable: Throwable) {
//                Result.failure(throwable)
//            }
//        }
//    }
    override suspend fun getCharacterDetails(id: Int): Result<CharacterDetails> {
        return withContext(Dispatchers.IO) {
            Result.success(remoteRepository.getCharacterDetails(characterId = id).toItemCharacter())
        }
//        return safeApiCall {
//            remoteRepository.getCharacterDetails(characterId = id).toItemCharacter()
//        }
    }
}

open class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Result.failure(throwable)
            }
        }
    }
}