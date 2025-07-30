package com.example.spaceflights.data


import com.example.spaceflights.data.remote.ApiService
import com.example.spaceflights.data.remote.SpaceFlightsApi
import com.example.spaceflights.local.AppDatabase
import com.example.spaceflights.local.LocationDao

class DatabaseRepository( //Schnittstelle zwischen der lokalen Datenbank und der Remote-API
    private val database: AppDatabase, //lokale Room Datenbank
    val apiService: SpaceFlightsApi //Daten von der Remote-API
) {

    //ermöglicht, auf die Locations-Tabelle der lokalen Datenbank zuzugreifen
    private  val locationDao:LocationDao = database.locationDao
    suspend fun loadLocations(){
        val response = apiService.retrofitService.getLocations() //API Abfrage um Liste von Standorte abzurufen

        response.results?.let { locations -> //Standorte in Datenbank abgespeichert (wenn nicht null)

            locationDao.insertAll(locations) //Standorte werden abgerufen
        }
    }


    val getAllLocations = locationDao.getAllLocations() //alle Standorte werden gespeichert und abgerufen

}