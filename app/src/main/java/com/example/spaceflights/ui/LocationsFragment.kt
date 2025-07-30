package com.example.spaceflights.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.spaceflights.MainViewModel
import com.example.spaceflights.R
import com.example.spaceflights.adapter.LocationAdapter
import com.example.spaceflights.databinding.FragmentLocationsBinding

class LocationsFragment : Fragment() { //Klasse Launches erbt von Fragment und zeigt eine Liste
    private lateinit var binding: FragmentLocationsBinding //greift auf das UI im Layout
    private val viewModel:MainViewModel by activityViewModels() //Fragment wird angezeigt

    override fun onCreate(savedInstanceState: Bundle?) { //Fragment wird erstellt bevor  UI aufgebaut wird
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Änderungen werden beobachtet. Bei Aktualisierung wird ein neuer Adapter mit den neuen Daten erstellt
    // und dem RecyclerView zugewiesen
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allLocations.observe(viewLifecycleOwner){ list->
            binding.rvLocations.adapter = LocationAdapter(list,viewModel)
        }
    }
}