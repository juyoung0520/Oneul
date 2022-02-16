package com.example.oneul.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CalendarTable")
data class Calender (
    var name: String,
    var color: Int,
    //ar dailySchedules: List<String>? = emptyList()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}