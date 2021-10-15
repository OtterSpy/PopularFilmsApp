package com.example.popularfilmsapp.data.remote

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

}