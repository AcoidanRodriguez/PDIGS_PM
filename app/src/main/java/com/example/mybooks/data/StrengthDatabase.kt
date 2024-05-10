package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Strength::class], version = 1, exportSchema = false)
abstract class StrengthDatabase : RoomDatabase() {
    abstract fun strengthDao(): StrengthDao

    companion object {
        @Volatile
        private var Instance: StrengthDatabase? = null

        fun getDatabase(context: Context): StrengthDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StrengthDatabase::class.java, "strengths_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
