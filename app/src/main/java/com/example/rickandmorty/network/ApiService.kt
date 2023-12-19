package com.example.rickandmorty.network

import com.example.rickandmorty.domain.model.CharacterDetailsDto
import com.example.rickandmorty.domain.model.CharactersDto
import com.example.rickandmorty.domain.model.EpisodeDto
import com.example.rickandmorty.utils.CHARACTERSPATH
import com.example.rickandmorty.utils.EPISODEID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(CHARACTERSPATH)
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("gender") gender: String? = null
    ): CharactersDto

    @GET("$CHARACTERSPATH/{character_id}")
    suspend fun getCharacterDetails(
        @Path("character_id") characterId: Int
    ): CharacterDetailsDto

    @GET("$EPISODEID{episode}")
    suspend fun getEpisode(
        @Path("episode") episode: String
    ): List<EpisodeDto>
}