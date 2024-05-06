package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Fr::class], version = 1, exportSchema = false)
abstract class FrDatabase : RoomDatabase() {
    abstract fun frDao(): FrDao

    companion object {
        @Volatile
        private var Instance: FrDatabase? = null

        fun getDatabase(context: Context): FrDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FrDatabase::class.java, "fr_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
