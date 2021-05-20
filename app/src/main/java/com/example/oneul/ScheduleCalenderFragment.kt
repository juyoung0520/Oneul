package com.example.oneul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oneul.databinding.FragmentScheduleCalenderBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter
import java.text.SimpleDateFormat

class ScheduleCalenderFragment: Fragment() {
    // 일정 캘린더, 일기/기분 캘린더

    private lateinit var binding: FragmentScheduleCalenderBinding
    private lateinit var sCalendarView:MaterialCalendarView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentScheduleCalenderBinding.inflate(layoutInflater)

        // Custom Calendar
        sCalendarView = binding.scheduleCalendarView

        // Header
        sCalendarView.setHeaderTextAppearance(R.style.CalendarHeader)
        var sf = SimpleDateFormat("MMMM");

        // WeekDays
        sCalendarView.setWeekDayTextAppearance(R.style.CalendarWeekdays)

        // Date
        sCalendarView.setDateTextAppearance(R.style.CalendarDate)


        return binding.root
    }
}