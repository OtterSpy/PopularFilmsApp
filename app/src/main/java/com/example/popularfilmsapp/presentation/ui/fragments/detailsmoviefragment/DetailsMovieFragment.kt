package com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.databinding.FragmentDetailsMovieBinding
import com.example.popularfilmsapp.presentation.ui.MainApplication.Companion.getAppComponent
import com.example.popularfilmsapp.presentation.ui.fragments.detailsmoviefragment.adapter.ActorsListAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailsMovieFragment : Fragment() {

    companion object {
        private const val NUM_STARS = 10
        private const val STEP_SIZE = 0.1f
    }

    private val args: DetailsMovieFragmentArgs by navArgs()

    private var _binding: FragmentDetailsMovieBinding? = null
    private val binding get() = _binding!!

    private val actorsAdapter by lazy { ActorsListAdapter() }

    private val viewModel: ActorsListViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().title = args.movieItem.title

        _binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)

        getDataList()
        initObserver()

        setDataToView()

        return binding.root

    }

    private fun initObserver() {

        viewModel.actors.observe(viewLifecycleOwner, { actors ->
            when (actors) {
                is Resource.Failure -> {
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    actorsAdapter.submitList(actors.data)
                }
            }
        })

    }

    private fun getDataList() {
        viewModel.getActors(args.movieItem.id, Constants.API_KEY)
    }


    private fun setDataToView() {
        Glide.with(this)
            .load(buildString {
                append(Constants.BASE_IMAGE_URL)
                append(args.movieItem.posterPath)
            })
            .into(binding.detailsImageView)

        binding.detailsTitleTextView.text = args.movieItem.title
        binding.detailsOverviewContentTextView.text = args.movieItem.overview

        if (args.movieItem.releaseDate != null && args.movieItem.releaseDate != "") {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val date = LocalDate.parse(
                    args.movieItem.releaseDate,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
                )
                binding.detailsReleaseDateTextView.text =
                    date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            } else {
                binding.detailsReleaseDateTextView.text = args.movieItem.releaseDate
            }


        } else {
            binding.detailsReleaseDateTextView.text = buildString { append("No Data") }
        }

        with(binding.movieRatingBar) {
            stepSize = STEP_SIZE
            numStars = NUM_STARS
            rating = args.movieItem.voteAverage.toFloat()
        }
        actorsAdapter.setOnClickListener { cast ->
            findNavController().navigate(
                DetailsMovieFragmentDirections.actionDetailsMovieFragmentToDetailsActorFragment(
                    cast
                )
            )
        }

        binding.detailsActorsRecyclerView.adapter = actorsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}