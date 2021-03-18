package com.cbellmont.datepickerejemplo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cbellmont.datepickerejemplo.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calendarView.date = System.currentTimeMillis()
        binding.calendarView.setOnDateChangeListener { calendar, year, month, day ->
            binding.tvDate.text = "La fecha escogida es $day del $month de $year"
        }

        binding.etDate.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val builder = MaterialDatePicker.Builder.dateRangePicker()
                builder.setSelection(androidx.core.util.Pair(System.currentTimeMillis(), System.currentTimeMillis()))
                val picker = builder.build()
                picker.show(supportFragmentManager, picker.toString())
                picker.addOnNegativeButtonClickListener { picker.dismiss() }
                picker.addOnPositiveButtonClickListener {
                    val calendar1 = Calendar.getInstance()
                    val calendar2 = Calendar.getInstance()

                    val date1 = it.first?.let { it1 -> Date(it1) }
                    val date2 = it.second?.let { it2 -> Date(it2) }
                    calendar1.time = date1
                    calendar2.time = date2

                    if (date1 != null && date2 != null )
                        binding.etDate.setText("The selected date range is ${calendar1.get(Calendar.DATE)} - ${calendar2.get(Calendar.DATE)}")
                }
            }
        }
    }
}