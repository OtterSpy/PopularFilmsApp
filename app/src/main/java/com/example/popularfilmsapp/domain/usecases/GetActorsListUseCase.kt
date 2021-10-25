package com.example.popularfilmsapp.domain.usecases

import android.util.Log
import com.example.popularfilmsapp.domain.model.Cast
import com.example.popularfilmsapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetActorsListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int, apiKey: String): List<Cast> {
        return try {
            repository.getActorsList(movieId, apiKey).cast
        } catch (e: Throwable) {
            Log.d("myLogs", "invokeError: $e")
            emptyList()
        }
    }
}