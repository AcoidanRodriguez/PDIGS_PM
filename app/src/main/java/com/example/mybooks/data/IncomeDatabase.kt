package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Income::class], version = 1, exportSchema = false)
abstract class IncomeDatabase : RoomDatabase() {
    abstract fun IncomeDao(): IncomeDao

    companion object {
        @Volatile
        private var Instance: IncomeDatabase? = null

        fun getDatabase(context: Context): IncomeDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, IncomeDatabase::class.java, "Incomes_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}