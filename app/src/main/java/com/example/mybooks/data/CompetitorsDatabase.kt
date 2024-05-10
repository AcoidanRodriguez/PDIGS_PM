package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Competitors::class], version = 1, exportSchema = false)
abstract class CompetitorsDatabase : RoomDatabase() {
    abstract fun CompetitorsDao(): CompetitorsDao

    companion object {
        @Volatile
        private var Instance: CompetitorsDatabase? = null

        fun getDatabase(context: Context): CompetitorsDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CompetitorsDatabase::class.java, "Competitors_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}