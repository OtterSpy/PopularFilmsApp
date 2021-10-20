package com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.databinding.FragmentDetailsMovieBinding
import com.example.popularfilmsapp.databinding.FragmentMovieListBinding
import com.example.popularfilmsapp.presentation.ui.MainApplication.Companion.getAppComponent
import com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment.adapter.ActorsListAdapter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DetailsMovieFragment : Fragment() {

    private val args: DetailsMovieFragmentArgs by navArgs()

    private var _binding: FragmentDetailsMovieBinding? = null
    private val binding get() = _binding!!

    private val actorsAdapter by lazy { ActorsListAdapter() }

    private val viewModel: ActorsListViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().title = args.movieItem.title

        _binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)

        getDataList()
        initObserver()

        Glide.with(this)
            .load(buildString {
                append(Constants.BASE_IMAGE_URL)
                append(args.movieItem.posterPath)
            })
            .into(binding.detailsImageView)

        binding.detailsTitleTextView.text = args.movieItem.title
        binding.detailsOverviewContentTextView.text = args.movieItem.overview

        if (args.movieItem.releaseDate != null && args.movieItem.releaseDate != "") {
            val date = LocalDate.parse(args.movieItem.releaseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            binding.detailsReleaseDateTextView.text = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        } else {
            binding.detailsReleaseDateTextView.text = buildString { append("No Data") }
        }

        with(binding.movieRatingBar) {
            stepSize = 0.1f
            numStars = 10
            rating = args.movieItem.voteAverage.toFloat()

            Log.d("TAG", "onCreateView: ${args.movieItem.voteAverage} $rating")
        }
        actorsAdapter.setOnClickListener { cast ->
            findNavController().navigate(
                DetailsMovieFragmentDirections.actionDetailsMovieFragmentToDetailsActorFragment(
                    cast
                )
            )
        }

        binding.detailsActorsRecyclerView.adapter = actorsAdapter

        return binding.root

    }

    private fun initObserver() {

        viewModel.actors.observe(viewLifecycleOwner, { actors ->
            when(actors) {
                is Resource.Failure -> {
                    Log.d("myLogs", "initObserver: Error")
                }
                is Resource.Loading -> {
                    Log.d("myLogs", "initObserver: Loading")
                }
                is Resource.Success -> {
                    Log.d("myLogs", "initObserver: Success ${actors.data}")
                    actorsAdapter.submitList(actors.data)
                }
            }
        })

    }

    private fun getDataList() {
        viewModel.getActors(args.movieItem.id, Constants.API_KEY)
        Log.d("myLogs", "getDataList: ${args.movieItem.id}")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}