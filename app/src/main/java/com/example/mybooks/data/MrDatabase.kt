package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mr::class], version = 1, exportSchema = false)
abstract class MrDatabase : RoomDatabase() {
    abstract fun mrDao(): MrDao

    companion object {
        @Volatile
        private var Instance: MrDatabase? = null

        fun getDatabase(context: Context): MrDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MrDatabase::class.java, "mr_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
