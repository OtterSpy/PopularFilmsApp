package com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.common.Util.getDp
import com.example.popularfilmsapp.databinding.FragmentMovieListBinding
import com.example.popularfilmsapp.presentation.helpers.GridSpacingItemDecoration
import com.example.popularfilmsapp.presentation.ui.MainApplication.Companion.getAppComponent
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.MovieListAdapter
import com.example.popularfilmsapp.presentation.ui.fragments.movielistfragment.adapter.MovieLoaderStateAdapter

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val movieAdapter by lazy { MovieListAdapter() }

    private val viewModel: MovieListViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().title = this.resources.getText(R.string.app_name)

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        initLoadMovies("")

        initObserver()

        setDataToView()

        return binding.root
    }

    private fun initObserver() {
        viewModel.movies.observe(viewLifecycleOwner, {
            movieAdapter.submitData(lifecycle, it)
        })
    }

    private fun initLoadMovies(query: String) {
        viewModel.passQuery(query)
    }

    private fun setDataToView() {
        binding.moviesRecyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                requireActivity().getDp(6f),
                true
            )
        )

        movieAdapter.setOnItemClickListener { movieItem ->
            findNavController().navigate(
                MovieListFragmentDirections.actionMovieListFragmentToDetailsMovieFragment(
                    movieItem
                )
            )
        }

        with(binding.searchEditText) {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    initLoadMovies(binding.searchEditText.text.toString())
                }
                false
            }

            addTextChangedListener {
                initLoadMovies(it.toString())
            }
        }

        binding.moviesRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.moviesRecyclerView.adapter =
            movieAdapter.withLoadStateFooter(MovieLoaderStateAdapter())
        movieAdapter.addLoadStateListener { state: CombinedLoadStates ->
            val refreshState = state.refresh
            binding.moviesRecyclerView.isVisible = refreshState != LoadState.Loading
            binding.listProgressBar.isVisible = refreshState == LoadState.Loading
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}