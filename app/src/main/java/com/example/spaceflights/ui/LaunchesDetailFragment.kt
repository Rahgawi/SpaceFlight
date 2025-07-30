package com.example.spaceflights.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spaceflights.MainViewModel
import com.example.spaceflights.databinding.FragmentLaunchesDetailBinding

class LaunchesDetailFragment : Fragment() {
    private lateinit var binding: FragmentLaunchesDetailBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val args: LaunchesDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Finde den Start anhand der ID, die durch das Fragment-Argument übergeben wurde
        viewModel.launches.observe(viewLifecycleOwner) { launch ->
            val launch = viewModel.launches.value?.find { it.id == args.launchID }
            launch?.let {

                if (launch != null) {
                    // Setze die Daten in die entsprechenden TextViews
                    binding.launchTimeTitle.text = launch.rocket.configuration.name // Name der Rakete
                    binding.launchType.text = launch.mission?.orbit?.name
                    binding.launchTime.text = launch.net  // Geplanter Startzeitpunkt
                    binding.launchPad.text = "${launch.pad.location.name}, ${launch.pad.latitude}, ${launch.pad.longitude}"  // Launchpad-Details

                    // Missionsdetails dynamisch gesetzt
                    binding.starlinkTitle.text = launch.mission?.name // Name der Mission
                    binding.starlinkDetails.text = launch.mission?.description

                } else {
                    // Falls launch null ist, kannst du eine Standardnachricht anzeigen oder eine andere Behandlung vornehmen
                    binding.launchTimeTitle.text = "Launch not found"

                }
            }
        }

        binding.backArrow.setOnClickListener{
            findNavController().navigate(LaunchesDetailFragmentDirections.actionLaunchesDetailFragmentToLaunchesFragment())
        }
    }
}