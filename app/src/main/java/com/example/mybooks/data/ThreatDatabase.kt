package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Threat::class], version = 1, exportSchema = false)
abstract class ThreatDatabase : RoomDatabase() {
    abstract fun threatDao(): ThreatDao

    companion object {
        @Volatile
        private var Instance: ThreatDatabase? = null

        fun getDatabase(context: Context): ThreatDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ThreatDatabase::class.java, "threats_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
