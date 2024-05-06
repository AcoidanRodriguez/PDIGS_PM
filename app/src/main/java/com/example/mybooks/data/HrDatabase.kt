package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Hr::class], version = 1, exportSchema = false)
abstract class HrDatabase : RoomDatabase() {
    abstract fun hrDao(): HrDao

    companion object {
        @Volatile
        private var Instance: HrDatabase? = null

        fun getDatabase(context: Context): HrDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, HrDatabase::class.java, "hr_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
