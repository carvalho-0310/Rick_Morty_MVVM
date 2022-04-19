package com.example.rickmortymvvm.data.local.page

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickmortymvvm.data.local.models.PagesLocal

@Dao
interface PagesLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savaPage(pagesLocal: PagesLocal)

    @Query("SELECT * FROM " + PagesLocal.TABLE_INFO)
    fun getPage(): PagesLocal
}
