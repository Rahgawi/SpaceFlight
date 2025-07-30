package com.example.spaceflights.data.model

data class Mission(
    val orbit: Orbit,
    val name:String,
    val description:String?,
    val type:String,

    )