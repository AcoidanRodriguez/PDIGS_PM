package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Opportunity::class], version = 1, exportSchema = false)
abstract class OpportunityDatabase : RoomDatabase() {
    abstract fun opportunityDao(): OpportunityDao

    companion object {
        @Volatile
        private var Instance: OpportunityDatabase? = null

        fun getDatabase(context: Context): OpportunityDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, OpportunityDatabase::class.java, "opportunities_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
