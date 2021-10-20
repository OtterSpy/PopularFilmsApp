package com.example.popularfilmsapp.data.remote

import com.example.popularfilmsapp.data.remote.dto.ActorDetailsDto
import com.example.popularfilmsapp.data.remote.dto.ActorMoviesListDto
import com.example.popularfilmsapp.data.remote.dto.ActorsListDto
import com.example.popularfilmsapp.data.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieListDto

    @GET("movie/{movie_id}/credits")
    suspend fun getActorsList(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): ActorsListDto

    @GET("person/{person_id}/movie_credits")
    suspend fun getActorMovieList(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): ActorMoviesListDto

    @GET("person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): ActorDetailsDto

    @GET("search/movie")
    suspend fun getSearchList(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    ): MovieListDto

}