package com.example.spaceflights.data.model

data class LocationResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Location>
)