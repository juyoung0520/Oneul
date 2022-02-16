package com.example.oneul.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CalenderDao {
    @Insert
    fun insert(calender: Calender)

    @Update
    fun update(calender: Calender)

    @Delete
    fun delete(calender: Calender)

    @Query("DELETE FROM CalendarTable")
    fun deleteAll()
}