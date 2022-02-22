package com.example.rickmortymvvm.models

import com.example.rickmortymvvm.util.adapter.DiffUtilGeneric.Compare
import java.io.Serializable

class Character(
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val character = other as Character
        if (id != character.id) return false
        return if (name != null) name == character.name else character.name == null
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }

    override fun isEqual(o: Character?): Boolean {
        return equals(o)
    }
}
