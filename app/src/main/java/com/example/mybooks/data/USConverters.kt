package com.example.mybooks.data

import androidx.room.TypeConverter

class USConverters {
    @TypeConverter
    fun fromStringList(list: List<String>): String{
        return list.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String):List<String>{
        return value.split(",")
    }
}