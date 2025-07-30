package com.example.spaceflights.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations") //Klasse Location als eine Entität, die in einer SQLite-Datenbank gespeichert wird. Die Tabelle in der Datenbank hat den Namen "locations"
data class Location(
    @PrimaryKey val id: Int, //Primärschlüssel eine Ganzzahl (Int), die jede Location eindeutig identifiziert
    val name: String,
    val timezone_name: String,
    val total_launch_count: Int


)