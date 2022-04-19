package com.example.rickmortymvvm.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickmortymvvm.data.local.models.CharacterLocal.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CharacterLocal(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val image: String,
    val species: String,
    val type: String,
    val gender: String,
    val created: String,
    val locationName: String,
    val locationUrl: String,
    val originName: String,
    val originUrl: String
) {

    companion object {
        const val TABLE_NAME = "character_local"
    }
}
