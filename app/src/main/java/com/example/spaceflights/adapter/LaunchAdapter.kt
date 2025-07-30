package com.example.spaceflights.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflights.MainViewModel
import com.example.spaceflights.data.model.Launch
import com.example.spaceflights.databinding.LaunchesItemBinding
import com.example.spaceflights.ui.LaunchesFragmentDirections

//LaunchAdapter erbt von RecyclerView
class LaunchAdapter( var dataset: List<Launch>, private val viewModel: MainViewModel) :
    RecyclerView.Adapter<LaunchAdapter.ItemViewHolder>() {
    //dataset: Liste der Launches - MainViewModel: Verwaltung von Daten
    inner class ItemViewHolder(val binding: LaunchesItemBinding) : // eine Zeile in der Liste
        RecyclerView.ViewHolder(binding.root) //Oberste View vom Layout

    //Neues Listenelement
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = LaunchesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)//Element wird erstellt und im Inflater angezeigt
        return ItemViewHolder(binding) //Element wird erstellt und Zurück gegeben
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position] //Daten werden aus dem dataset geholt
        holder.binding.launchTime.text = item.rocket.configuration.name
        holder.binding.launchPad.text = item.net
        holder.binding.launchType.text = "${item.pad.location.name}, ${item.pad.latitude}, ${item.pad.longitude}"
        holder.binding.longMarchTitle.text = item.name

        holder.itemView.setOnClickListener {
            // Navigiere zum Detail-Fragment
            holder.binding.detailsLink.findNavController().navigate(

                LaunchesFragmentDirections.actionLaunchesFragmentToLaunchesDetailFragment(item.id)
            )
        }
    }

    override fun getItemCount(): Int { //Gesamte Liste
        return dataset.size
    }
}