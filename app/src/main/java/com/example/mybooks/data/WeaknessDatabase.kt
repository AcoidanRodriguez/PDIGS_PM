package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weakness::class], version = 1, exportSchema = false)
abstract class WeaknessDatabase : RoomDatabase() {
    abstract fun weaknessDao(): WeaknessDao

    companion object {
        @Volatile
        private var Instance: WeaknessDatabase? = null

        fun getDatabase(context: Context): WeaknessDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WeaknessDatabase::class.java, "weaknesses_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
