
package com.example.spaceflights.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.spaceflights.MainViewModel
import com.example.spaceflights.databinding.FragmentLaunchesBinding
import com.example.spaceflights.adapter.LaunchAdapter

class LaunchesFragment : Fragment() { //Klasse Launches erbt von Fragment und zeigt eine Liste
    // TODO: Rename and change types of parameters
    private lateinit  var binding: FragmentLaunchesBinding //greift auf das UI im Layout
    private val viewModel: MainViewModel by activityViewModels() //Fragment wird angezeigt

    //    private lateinit var adapter: LaunchAdapter
    override fun onCreate(savedInstanceState: Bundle?) { //Fragment wird erstellt bevor  UI aufgebaut wird
        super.onCreate(savedInstanceState)
    }
    //Aufbau vom Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLaunchesBinding.inflate(inflater, container, false)
        viewModel.getLaunches() //Sobald das Fragment steht, werden die Launches angezeigt
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {  //Layout ist vollständig geladen
        super.onViewCreated(view, savedInstanceState)

        //neuer LaunchAdapter mit leerer Liste und das MainViewModel als Parameter
        val adapter = LaunchAdapter(emptyList(),viewModel)
        binding.rvLaunches.adapter = adapter  //Adaper wird RecyclerView zugewiesen

        //Änderungen werden beobachtet. Bei Aktualisierung wird ein neuer Adapter mit den neuen Daten erstellt
        // und dem RecyclerView zugewiesen
        viewModel.launches.observe(viewLifecycleOwner) { list ->
            binding.rvLaunches.adapter = LaunchAdapter(list,viewModel)
        }
    }
}