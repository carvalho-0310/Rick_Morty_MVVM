package com.example.rickmortymvvm.presentation.models

import com.example.rickmortymvvm.app.util.adapter.DiffUtilGeneric.Compare
import java.io.Serializable

data class Character(
    override val id: Int,
    val name: String?,
    val status: String,
    val image: String,
    val species: String,
    val type: String,
    val gender: String,
    val created: String
) : Serializable, Compare<Character?> {
    val location: Location? = null
    val origin: Origin? = null

    override fun isEqual(o: Character?): Boolean {
        return equals(o)
    }
}
