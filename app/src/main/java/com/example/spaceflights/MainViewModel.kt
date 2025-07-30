package com.example.spaceflights

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflights.data.DatabaseRepository
import com.example.spaceflights.data.model.Launch
import com.example.spaceflights.data.model.LaunchResponse
import com.example.spaceflights.data.model.Location
import com.example.spaceflights.data.remote.SpaceFlightsApi
import com.example.spaceflights.data.repository.Repository
import com.example.spaceflights.local.AppDatabase.Companion.getDatabase
import kotlinx.coroutines.launch


//Daten zwischen Repository (Datenquelle) und der View (UI) übermittelt
class MainViewModel(application:Application): AndroidViewModel(application) { //erbt von AndroidViewModel

    private val repository = Repository() //Daten von API oder Datenbank werden aufgerufen
    val launches = repository.launches //Flüge werden ausgelesen
    val error: LiveData<String> = repository.error //Fehlermeldung

    private val _allLocations = MutableLiveData<List<Location>>() //Liste aller Standorte

    private val database = getDatabase(application) //Verbindung zur lokalen Datenbank
    val apiService = SpaceFlightsApi //externe API
    private val databaseRepo = DatabaseRepository(database,apiService) //Wird von der lokalen Datenbank als auch von der API genutzt

    init {
        refreshLocations()
    }
    val allLocations = databaseRepo.getAllLocations //Zugreifen aus alle Standorte

    fun getLaunches() {
        viewModelScope.launch{ repository.getLaunches() } } //Flüge werden von der Repository geladen


    fun refreshLocations(){ //Standorte werden aus der Datenbank oder API geladen
        viewModelScope.launch {
            databaseRepo.loadLocations()
        }
    }
}