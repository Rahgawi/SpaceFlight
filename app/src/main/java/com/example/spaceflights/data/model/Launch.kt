package com.example.spaceflights.data.model
// Die Datenklasse enthält mehrere Felder, die Informationen über einen Raketenstart speichern.
data class Launch(
    val id: String,
    val name: String,
    val net: String,
    val rocket: Rocket,
    val pad: Pad,
    val image: String?,
    val mission: Mission?,
    val status: Status

)