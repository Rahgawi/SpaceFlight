package com.example.spaceflights.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spaceflights.data.model.Location

@Database(entities = [Location::class],version = 2, exportSchema = false)
abstract class AppDatabase:RoomDatabase() { //Erweiterung der RoomDatabase, Basis für die Datenbank
    abstract val  locationDao:LocationDao //Data Access Object - Zugriff auf die Datenbank (z. B. zum Einfügen, Aktualisieren, Löschen von Daten)

    companion object { //Sicherstellung das nur eine Instanz existiert
        @Volatile //Sicherstellung das Änderungen sofort sichtbar sind
        private var INSTANCE: AppDatabase? = null //ist null und nur einmalig initialisiert

        fun getDatabase(context: Context): AppDatabase {  //Funktion an Datenbank, ggf. neue Instanz erstellt wenn nicht vorhanden
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder( //Erstellung der AppDatabase
                    context.applicationContext, //Anwendungskontext
                    AppDatabase::class.java, //Datenklasse
                    "space_flights_database" //Name
                ).build()
                INSTANCE = instance
                instance//Sicherstellung das nur einen Instanz erstellt wird
            }
        }
    }
}