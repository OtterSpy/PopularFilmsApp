package com.example.popularfilmsapp.presentation.ui.fragments.detailsactorfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.popularfilmsapp.R
import com.example.popularfilmsapp.common.Resource
import com.example.popularfilmsapp.presentation.ui.MainApplication.Companion.getAppComponent

class DetailsActorFragment : Fragment() {

    private val args: DetailsActorFragmentArgs by navArgs()

    private val viewModelMoviesList: ActorMoviesListCastViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }
    private val viewModelActorDetails: ActorDetailsViewModel by viewModels {
        requireActivity().getAppComponent().factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        initObserver()

        getActorMoviesList()

        return inflater.inflate(R.layout.fragment_details_actor, container, false)
    }

    private fun initObserver() {
        viewModelMoviesList.actorFilms.observe(viewLifecycleOwner, { movies ->
            when(movies) {
                is Resource.Failure -> {
                    Log.d("myLogs", "initObserverFailure: ")
                }
                is Resource.Loading -> {
                    Log.d("myLogs", "initObserverLoading: ")
                }
                is Resource.Success -> {
                    Log.d("myLogs", "initObserverSuccess: ${movies.data.size}")
                }
            }
        })
        viewModelActorDetails.actorDetails.observe(viewLifecycleOwner, { actor ->
            when(actor) {
                is Resource.Failure -> {
                    Log.d("myLogs", "initObserverFailureActor: ")
                }
                is Resource.Loading -> {
                    Log.d("myLogs", "initObserverLoadingActor: ")
                }
                is Resource.Success -> {
                    Log.d("myLogs", "initObserverSuccessActor: ${actor.data}")
                }
            }
        })
    }

    private fun getActorMoviesList() {
        viewModelMoviesList.getActorFilms(args.actorItem.id)
        viewModelActorDetails.getActorDetails(args.actorItem.id)
    }

}