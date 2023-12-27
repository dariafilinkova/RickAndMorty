package com.example.rickandmorty.network

import com.example.rickandmorty.domain.model.character.CharacterDetailsDto
import com.example.rickandmorty.domain.model.character.CharactersDto
import com.example.rickandmorty.domain.model.episode.EpisodeDto
import com.example.rickandmorty.domain.model.episode.EpisodesDto
import com.example.rickandmorty.domain.model.location.LocationResultDto
import com.example.rickandmorty.domain.model.location.LocationsDto
import com.example.rickandmorty.utils.CHARACTERID
import com.example.rickandmorty.utils.CHARACTERSPATH
import com.example.rickandmorty.utils.EPISODEID
import com.example.rickandmorty.utils.EPISODESPATH
import com.example.rickandmorty.utils.LOCATIONID
import com.example.rickandmorty.utils.LOCATIONSPATH
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

    @GET("$EPISODEID{episode}")
    suspend fun getOneEpisodeForCharacterDetail(
        @Path("episode") episode: String
    ): EpisodeDto

    @GET("$EPISODESPATH/{episode_id}")
    fun getEpisodeDetails(
        @Path("episode_id") episodeId: Int
    ): EpisodeDto

    @GET("$EPISODESPATH/{id}")
    suspend fun getEpisode(
        @Path("id") id: Int
    ): EpisodeDto

    @GET(EPISODESPATH)
    suspend fun getEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("episode") status: String? = null
    ): EpisodesDto
    @GET(LOCATIONSPATH)
    suspend fun getLocations(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("type") status: String? = null,
        @Query("dimension") gender: String? = null
    ): LocationsDto

    @GET("$LOCATIONSPATH/{location_id}")
    suspend fun getLocationDetails(
        @Path("location_id") locationId: Int
    ): LocationResultDto

    @GET("$LOCATIONID{location}")
    suspend fun getLocationForDetail(
        @Path("location") location: String
    ): LocationResultDto

    @GET("$CHARACTERID{character}")
    suspend fun getCharactersForDetail(
        @Path("character") character: String
    ): List<CharacterDetailsDto>
}