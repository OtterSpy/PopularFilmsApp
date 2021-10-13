package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularfilmsapp.common.Util.getDp
import com.example.popularfilmsapp.databinding.FragmentMovieListBinding
import com.example.popularfilmsapp.presentation.helpers.GridSpacingItemDecoration
import com.example.popularfilmsapp.presentation.ui.MainApplication.Companion.getAppComponent
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapters.MovieListAdapter
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapters.MovieLoaderStateAdapter

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val movieAdapter by lazy { MovieListAdapter(requireActivity()) }

    private val viewModel: MovieListViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        binding.moviesRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                requireActivity().getDp(6f),
                true
            )
        )
        binding.moviesRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.moviesRecyclerView.adapter =
            movieAdapter.withLoadStateFooter(MovieLoaderStateAdapter())
        movieAdapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.moviesRecyclerView.isVisible = state.refresh != LoadState.Loading
            binding.listProgressBar.isVisible = state.refresh == LoadState.Loading
        }

        initObserver()

        return binding.root
    }

    private fun initObserver() {
        viewModel.movies.observe(viewLifecycleOwner, { resources ->
            movieAdapter.submitData(lifecycle, resources)
        })
    }

//    private fun initLoadProducts() {
//        viewModel.getMovies(page = 1)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}