package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Stakeholders::class], version = 1, exportSchema = false)
abstract class StakeholdersDatabase : RoomDatabase() {
    abstract fun StakeholdersDao(): StakeholdersDao

    companion object {
        @Volatile
        private var Instance: StakeholdersDatabase? = null

        fun getDatabase(context: Context): StakeholdersDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StakeholdersDatabase::class.java, "Stakeholders_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}