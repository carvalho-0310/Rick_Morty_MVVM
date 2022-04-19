package com.example.rickmortymvvm.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickmortymvvm.data.local.models.PagesLocal.Companion.TABLE_INFO

@Entity(tableName = TABLE_INFO)
data class PagesLocal(
    var page: Int = 1
) {
    @PrimaryKey
    var uid = 0

    companion object {
        const val TABLE_INFO = "information_table"
    }
}
