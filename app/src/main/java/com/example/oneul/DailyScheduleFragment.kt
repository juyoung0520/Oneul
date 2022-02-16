package com.example.oneul

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.oneul.databinding.FragmentDailyScheduleBinding
import com.example.oneul.databinding.FragmentScheduleBinding
import com.example.oneul.databinding.ItemScheduleBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

class DailyScheduleFragment : DialogFragment() {
    private lateinit var binding: FragmentDailyScheduleBinding
    private lateinit var currentDate: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDailyScheduleBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext(), R.style.Viewpager_dialog)

        arguments?.let {
            currentDate = it.getString("currentDate")!!
        }

        initViewpager()

        dialog.setContentView(binding.root)
        dialog.setCanceledOnTouchOutside(true)

        return dialog
    }

    fun initViewpager() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.viewpagerDailySchedule.adapter = DailyScheduleAdapter(requireContext())
        binding.viewpagerDailySchedule.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpagerDailySchedule.offscreenPageLimit = 1
        binding.viewpagerDailySchedule.currentItem = 0
        binding.viewpagerDailySchedule.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
    }

    // 일정 전체 adapter
    inner class DailyScheduleAdapter(val context: Context) : RecyclerView.Adapter<DailyScheduleAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = FragmentScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return 1
        }

        inner class ViewHolder(val binding: FragmentScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(position: Int) {
                binding.imageAdd.setOnClickListener {
                    val intent= Intent(context, AddScheduleActivity::class.java)
                    startActivity(context, intent, null)
                }

                binding.textDate.text = currentDate

//                val dateformatter: DateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
//                val today = Date(System.currentTimeMillis())
//                when(position) {
//                    // 날짜 고치기
//                    0 -> binding.textDate.text = "2021년 06월 10일"
//                    1 -> binding.textDate.text = dateformatter.format(today).toString()
//                    2 -> binding.textDate.text = "2021년 06월 12일"
//                    3 -> binding.textDate.text = "2021년 06월 13일"
//                    4 -> binding.textDate.text = "2021년 06월 14일"
//                }

                binding.recyclerSchedule.adapter = ScheduleAdapter(position%4)
                binding.recyclerSchedule.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    // 상세 일정 adapter
    class ScheduleAdapter(val p: Int) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

        inner class ScheduleViewHolder(val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(position: Int) {
                if(position%3 == 0) {
                    binding.textTimeStart.visibility = View.VISIBLE
                    binding.textTimeStart.text = "07:00"
                    binding.textTimeEnd.visibility = View.VISIBLE
                    binding.textTimeEnd.text = "08:00"
                    binding.textAllDay.visibility = View.GONE
                } else if(position%3 == 1) {
                    binding.textScheduleName.text = "운동"
                    binding.textTimeStart.visibility = View.VISIBLE
                    binding.textTimeStart.text = "03:00"
                    binding.textTimeEnd.visibility = View.VISIBLE
                    binding.textTimeEnd.text = "04:00"
                    binding.textAllDay.visibility = View.GONE
                } else {
                    binding.textScheduleName.text = "종강"
                    binding.textTimeStart.visibility = View.GONE
                    binding.textTimeEnd.visibility = View.GONE
                    binding.textAllDay.visibility = View.VISIBLE
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
            val binding = ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ScheduleViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
            val view = holder.itemView
        }

        override fun getItemCount(): Int {
            return p
        }
    }
}