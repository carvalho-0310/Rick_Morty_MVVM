package com.example.rickmortymvvm.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickmortymvvm.data.local.CharacterLocalDao
import com.example.rickmortymvvm.data.local.models.CharacterLocal
import com.example.rickmortymvvm.data.local.models.PagesLocal

@Database(entities = [CharacterLocal::class, PagesLocal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterLocalDao

    companion object {

        private const val DATABASE_NAME: String = "local info"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
