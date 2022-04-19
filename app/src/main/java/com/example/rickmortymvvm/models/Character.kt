package com.example.rickmortymvvm.models

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
    val created: String,
    val location: Location,
    val origin: Origin
) : Serializable, Compare<Character?> {

    override fun isEqual(o: Character?): Boolean {
        return equals(o)
    }
}
