package com.example.popularfilmsapp.presentation.helpers.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.popularfilmsapp.domain.model.MovieItem

class MoviePagingSource : PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        TODO("Not yet implemented")
    }
}