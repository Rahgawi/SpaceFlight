package com.example.spaceflights.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spaceflights.data.model.Location
@Dao //Methoden für Datenbankoperationen (z. B. Einfügen, Abrufen von Daten)
interface LocationDao {
    @Insert(onConflict  = OnConflictStrategy.REPLACE) //einfügen in Liste, bei gleichen IDs wird die alte duch die neue ersetzt
    suspend fun insertAll(locations:List<Location>) //Liste wir in Datenbank eingefügt

    @Query("SELECT * FROM locations") //alle Einträge werden aus der Tabelle geholt
    fun getAllLocations():LiveData<List<Location>>

}