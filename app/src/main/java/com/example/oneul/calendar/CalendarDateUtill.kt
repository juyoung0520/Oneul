package com.example.oneul.calendar

import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

fun calendarDayToString(date: CalendarDay): String {
    return "${date.year}년 ${date.month}월 ${date.day}일 ${getDayOfWeek(date)}요일"
}

fun getDayOfWeek(date: CalendarDay): String {
    val week = arrayOf("", "일", "월", "화", "수", "목", "금", "토")

    val calendar: Calendar = Calendar.getInstance()
    calendar.set(date.year, date.month-1, date.day)

    return week[calendar.get(Calendar.DAY_OF_WEEK)]
}