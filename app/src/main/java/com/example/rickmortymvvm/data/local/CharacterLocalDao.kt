package com.example.rickmortymvvm.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmortymvvm.data.local.models.CharacterLocal
import io.reactivex.Observable

@Dao
interface CharacterLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savaCharacter(characterLocal: CharacterLocal)

    @Query("SELECT * FROM ${CharacterLocal.TABLE_NAME}")
    fun getList(): Observable<List<CharacterLocal>>

    @Query("DELETE FROM ${CharacterLocal.TABLE_NAME}")
    fun clearLocalList()
}
