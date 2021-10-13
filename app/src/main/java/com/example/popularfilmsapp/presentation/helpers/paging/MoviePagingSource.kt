package com.example.popularfilmsapp.presentation.helpers.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.domain.model.MovieItem
import com.example.popularfilmsapp.domain.usecases.GetMoviesListUseCase
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase
) : PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val page = params.key ?: 1

        val movies = getMoviesListUseCase(Constants.API_KEY, page)

        val nextKey = page + 1
        val prevKey = if (page == 1) null else page - 1
        Log.d("myLogs", "load: $movies ")
        return LoadResult.Page(movies, prevKey, nextKey)
    }
}