package com.example.mybooks.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ir::class], version = 1, exportSchema = false)
abstract class IrDatabase : RoomDatabase() {
    abstract fun irDao(): IrDao

    companion object {
        @Volatile
        private var Instance: IrDatabase? = null

        fun getDatabase(context: Context): IrDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, IrDatabase::class.java, "ir_table")//
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
