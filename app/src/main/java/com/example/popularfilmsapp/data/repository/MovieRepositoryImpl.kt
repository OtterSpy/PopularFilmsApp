package com.example.popularfilmsapp.data.repository

import com.example.popularfilmsapp.data.remote.TheMovieDbApi
import com.example.popularfilmsapp.data.remote.dto.toActorDetails
import com.example.popularfilmsapp.data.remote.dto.toActorMoviesList
import com.example.popularfilmsapp.data.remote.dto.toActorsList
import com.example.popularfilmsapp.data.remote.dto.toMovieList
import com.example.popularfilmsapp.domain.model.ActorDetails
import com.example.popularfilmsapp.domain.model.ActorMoviesList
import com.example.popularfilmsapp.domain.model.ActorsList
import com.example.popularfilmsapp.domain.model.MovieList
import com.example.popularfilmsapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val theMovieDbApi: TheMovieDbApi
) : MovieRepository {

    override suspend fun getMoviesList(apiKey: String, page: Int): MovieList =
        theMovieDbApi.getMovieList(apiKey, page).toMovieList()

    override suspend fun getActorsList(movieId: Int, apiKey: String): ActorsList =
        theMovieDbApi.getActorsList(movieId, apiKey).toActorsList()

    override suspend fun getActorMoviesList(personId: Int, apiKey: String): ActorMoviesList =
        theMovieDbApi.getActorMovieList(personId, apiKey).toActorMoviesList()

    override suspend fun getActorDetails(personId: Int, apiKey: String): ActorDetails =
        theMovieDbApi.getActorDetails(personId, apiKey).toActorDetails()

    override suspend fun getSearchList(query: String, apiKey: String, page: Int): MovieList =
        theMovieDbApi.getSearchList(query, apiKey, page).toMovieList()

}