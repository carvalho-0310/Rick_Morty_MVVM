package com.example.rickmortymvvm.models

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val image: String,
    val species: String,
    val type: String,
    val gender: String,
    val created: String,
    val location: Location,
    val origin: Origin
)
