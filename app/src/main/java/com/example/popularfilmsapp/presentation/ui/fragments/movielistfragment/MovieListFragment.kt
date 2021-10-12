package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.common.Util.getDp
import com.example.popularfilmsapp.databinding.FragmentMovieListBinding
import com.example.popularfilmsapp.presentation.helpers.GridSpacingItemDecoration
import com.example.popularfilmsapp.presentation.ui.MainApplication.Companion.getAppComponent

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
                requireActivity().getDp(5f),
                true
            )
        )
        binding.moviesRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.moviesRecyclerView.adapter = movieAdapter


        initObserver()

        initLoadProducts()


        return binding.root
    }

    private fun initObserver() {
        viewModel.movies.observe(viewLifecycleOwner, { resources ->
            when(resources) {
                is Resource.Failure -> {
                    throw error("Failure load data")
                }
                is Resource.Loading -> {
                    Log.d("myLogs", "initObserver: Loading")
                }
                is Resource.Success -> {
                    Log.d("myLogs", "initObserver: ${resources.data}")
                    movieAdapter.mySubmitList(resources.data)
                }
            }
        })
    }

    private fun initLoadProducts() {
        viewModel.getMovies(page = 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}