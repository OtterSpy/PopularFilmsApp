package com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.common.Constants
import com.example.popularfilmsapp.common.Constants.NO_DATA
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.databinding.FragmentDetailsActorBinding
import com.example.popularfilmsapp.domain.model.ActorDetails
import com.example.popularfilmsapp.domain.model.toMovieItem
import com.example.popularfilmsapp.presentation.ui.MainApplication.Companion.getAppComponent
import com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment.adapter.ActorCastListAdapter

class DetailsActorFragment : Fragment() {

    private val args: DetailsActorFragmentArgs by navArgs()

    private var _binding: FragmentDetailsActorBinding? = null
    private val binding get() = _binding!!

    private val actorCastAdapter by lazy { ActorCastListAdapter() }

    private val viewModelMoviesList: ActorMoviesListCastViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }
    private val viewModelActorDetails: ActorDetailsViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().title = args.actorItem.name

        _binding = FragmentDetailsActorBinding.inflate(inflater, container, false)

        initObserver()

        getActorMoviesList()

        actorCastAdapter.setOnClickListener { actorMovieCast ->
            findNavController().navigate(
                DetailsActorFragmentDirections.actionDetailsActorFragmentToDetailsMovieFragment(
                    actorMovieCast.toMovieItem()
                )
            )
        }

        binding.actingRecyclerView.adapter = actorCastAdapter

        return binding.root
    }

    private fun initObserver() {
        viewModelMoviesList.actorFilms.observe(viewLifecycleOwner, { movies ->
            when (movies) {
                is Resource.Failure -> {
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    actorCastAdapter.submitList(movies.data)
                }
            }
        })
        viewModelActorDetails.actorDetails.observe(viewLifecycleOwner, { actor ->
            when (actor) {
                is Resource.Failure -> {
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    setDataToView(actor.data)
                }
            }
        })
    }

    private fun setDataToView(actor: ActorDetails) {

        if (actor.profilePath != null) {
            Glide.with(this)
                .load(buildString {
                    append(Constants.BASE_IMAGE_URL)
                    append(actor.profilePath)
                })
                .into(binding.profileImageView)
        } else {
            binding.profileImageView.scaleType = ImageView.ScaleType.CENTER
            binding.profileImageView.setImageResource(R.drawable.ic_actor_place_holder_image)
        }
        binding.nameTextView.text = actor.name
        if (actor.birthday != null) {
            binding.birthdayTextView.text = SpannableStringBuilder()
                .bold {
                    append("Birthday: ")
                }
                .append(actor.birthday)
        } else {
            binding.birthdayTextView.text = SpannableStringBuilder()
                .bold {
                    append("Birthday: ")
                }
                .append(NO_DATA)
        }
        binding.knownForTextView.text = SpannableStringBuilder()
            .bold {
                append("Known For: ")
            }
            .append(actor.knownForDepartment)
        if (actor.placeOfBirth != null) {
            binding.placeOfBirthTextView.text = SpannableStringBuilder()
                .bold {
                    append("Place of Birthday: ")
                }
                .append(actor.placeOfBirth)
        } else {
            binding.placeOfBirthTextView.text = SpannableStringBuilder()
                .bold {
                    append("Place of Birthday: ")
                }
                .append(NO_DATA)
        }
        if (actor.biography != null && actor.biography != "") {
            binding.biographyContentTextView.text = actor.biography
        } else {
            binding.biographyContentTextView.text = buildString { append(NO_DATA) }
        }
    }

    private fun getActorMoviesList() {
        viewModelMoviesList.getActorFilms(args.actorItem.id)
        viewModelActorDetails.getActorDetails(args.actorItem.id)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}