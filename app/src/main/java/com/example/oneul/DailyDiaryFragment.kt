package com.example.oneul

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.oneul.data.Diary
import com.example.oneul.databinding.FragmentDailyDiaryBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
import com.example.oneul.calendar.calendarDayToString
import com.google.android.play.core.install.model.ActivityResult


class DailyDiaryFragment : Fragment() {
    private lateinit var binding: FragmentDailyDiaryBinding
    private lateinit var currentDate: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentDailyDiaryBinding.inflate(layoutInflater)

        arguments?.let {
            currentDate = it.getString("currentDate")!!
        }

        binding.dialydiaryDateTv.text = currentDate

        binding.imageButton.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.imageView4.setOnClickListener() {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        return binding.root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && data != null){
            if(requestCode == 1){
                var imageUri : Uri? = data?.data
                binding.imageView4.setImageURI(imageUri)
            }
        }
    }
}