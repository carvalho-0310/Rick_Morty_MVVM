package com.example.rickmortymvvm.presentation.models

import com.example.rickmortymvvm.app.util.adapter.DiffUtilGeneric.Compare
import java.io.Serializable

data class CharacterVM(
    override val id: Int,
    val name: String,
    val status: String,
    val image: String,
    val species: String,
    val type: String,
    val gender: String,
    val created: String,
    val locationVM: LocationVM,
    val originVM: OriginVM
) : Serializable, Compare<CharacterVM> {

    override fun isEqual(o: CharacterVM): Boolean {
        return equals(o)
    }
}
