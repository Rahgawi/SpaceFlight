package com.example.spaceflights.adapter



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflights.MainViewModel
import com.example.spaceflights.data.model.Location
import com.example.spaceflights.databinding.LocationItemBinding

class LocationAdapter(private val dataset:List<Location>, private val viewModel:MainViewModel):RecyclerView.Adapter<LocationAdapter.ItemViewHolder>() {
    //dataset: Liste der Launches - MainViewModel: Verwaltung von Daten

    inner class ItemViewHolder(val binding:LocationItemBinding):RecyclerView.ViewHolder(binding.root) // eine Zeile in der Liste

    //Neues Listenelement
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapter.ItemViewHolder {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)//Element wird erstellt und im Inflater angezeigt
        return ItemViewHolder(binding) //Element wird erstellt und Zurück gegeben
    }

    override fun onBindViewHolder(holder: LocationAdapter.ItemViewHolder, position: Int) {
        val location = dataset[position] //Daten werden aus dem dataset geholt
        holder.binding.titleAndoya.text = location.name
        holder.binding.subtitleAndoya.text = location.timezone_name
    }

    override fun getItemCount(): Int { //Gesamte Liste
        return dataset.size
    }
}