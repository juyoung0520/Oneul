package com.example.oneul.calendar

//import DiaryDecorator
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.oneul.calendar.decorator.OneDayDecorator
import com.example.oneul.R
import com.example.oneul.calendar.decorator.Diary2Decorator
import com.example.oneul.calendar.decorator.DiaryDecorator
import com.example.oneul.data.Diary
import com.example.oneul.databinding.FragmentDiaryCalenderBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.time.format.DateTimeFormatter

class DiaryCalenderFragment: Fragment() {
    // 일기/기분 캘린더
    private lateinit var binding: FragmentDiaryCalenderBinding
    private lateinit var dCalendarView:MaterialCalendarView

    private lateinit var oneDayDecorator: OneDayDecorator
    private lateinit var diaryDecorator: DiaryDecorator
    private lateinit var diary2Decorator:Diary2Decorator

    private var dateList : ArrayList<Diary> = ArrayList<Diary>()
    private var calenderdayList : ArrayList<CalendarDay> = ArrayList<CalendarDay>()
    private var moodList = mutableListOf<Drawable>()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentDiaryCalenderBinding.inflate(layoutInflater)

        // Custom Calendar
        dCalendarView = binding.diaryCalendarView

        // Header
        dCalendarView.setHeaderTextAppearance(R.style.CalendarHeader)
        var monthFormat = DateTimeFormatter.ofPattern("MMMM");
        //var title: TitleFormatter = monthFormat
        //dCalendarView.setTitleFormatter { sf }
        //dCalendarView.setTitleFormatter(monthFormat)

        // WeekDays
        dCalendarView.setWeekDayTextAppearance(R.style.CalendarWeekdays)

        // Date
        dCalendarView.setDateTextAppearance(R.style.CalendarDate)


        // Mood list 저장
        moodList.add(context?.getDrawable(R.drawable.joy)!!)
        moodList.add(context?.getDrawable(R.drawable.tired)!!)
        moodList.add(context?.getDrawable(R.drawable.angry)!!)
        moodList.add(context?.getDrawable(R.drawable.enjoy)!!)
        moodList.add(context?.getDrawable(R.drawable.gloom)!!)
        moodList.add(context?.getDrawable(R.drawable.just)!!)
        moodList.add(context?.getDrawable(R.drawable.sad)!!)

//
//        // 6월
//        for (i:Int in 1..15 step(3)){
//            var new = CalendarDay.from(2021,6,i)
//            calenderdayList.add(new)
//        }
//        calenderdayList.add(CalendarDay.from(2021,6,14))

        // 감정 + 날짜 calendar에 전달
        var i=0
        for (calDay in calenderdayList) {
            var mood = moodList[i++%7]
            diary2Decorator = Diary2Decorator(calDay,mood)
            dCalendarView.addDecorators(diary2Decorator)
        }

        // 오늘 날짜 primary 색깔로
        oneDayDecorator = OneDayDecorator()
        dCalendarView.addDecorators(oneDayDecorator)

        //날짜 누르면 일기 볼 수 있게
        dCalendarView.setOnDateChangedListener { widget, date, selected ->
            val diary = Diary(date = date.toString())
            val bundle = bundleOf("currentDate" to calendarDayToString(date))
            findNavController().navigate(R.id.action_calenderFragment_to_dailyDiaryFragment, bundle)
        }

        return binding.root
    }
}