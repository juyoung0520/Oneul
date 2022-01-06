package com.example.oneul.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prolificinteractive.materialcalendarview.CalendarDay

@Entity(tableName = "DiaryTable")
data class Diary (
    var date: String,
    var imagesUrl: String? = null,
    var mood: Int? = null,
    var diary: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}