package com.example.latihan2.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.latihan2.R
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = DetailFragmentArgs.fromBundle(arguments!!).taskData

        layout_task_detail.setBackgroundColor(
            ContextCompat.getColor(requireContext(), colorTask(data?.colorTask!!))
        )
        title_task_detail.setText(data.titleTask)
        detail_task_detail.setText(data.detailTask)
        category_task_detail.setText(data.categoryTask)
        reminder_task_detail.visibility = reminderView(data.reminderSet!!)
        reminder_task_detail.setText(reminderTime(data.reminderTask!!))
        date_task_detail.setText(dateFormat(data.dateTask!!.time))
        
    }

    private fun dateFormat(dateInput: Long) : String {
        var dateOut = "null"
        val dateNow = Date().time
        val dateDiffer = dateNow - dateInput

        val second = TimeUnit.MILLISECONDS.toSeconds(dateDiffer)
        val minute = TimeUnit.MILLISECONDS.toMinutes(dateDiffer)
        val hour = TimeUnit.MILLISECONDS.toHours(dateDiffer)
        val day = TimeUnit.MILLISECONDS.toDays(dateDiffer)

        if(second < 60){
            dateOut = "${second}s ago"
        }
        else if(second > 60 && minute < 60){
            dateOut= "${minute}m ago"
        }
        else if(minute > 60 && hour <= 24){
            dateOut= "${hour}h ago"
        }
        else if(hour > 24 && day <= 7){
            dateOut= "${day}d ago"
        }
        else{
            val format = SimpleDateFormat("dd/MM/yy")
            dateOut = format.format(dateInput).toString()
        }

        return dateOut
    }

    private fun colorTask(colorInput : Int) : Int {
        var data : Int = R.color.colorPrimary

        when(colorInput){
            1 -> data = R.color.color1
            2 -> data = R.color.color2
            3 -> data = R.color.color3
        }

        return data
    }

    private fun reminderView(reminderSet : Boolean) : Int {
        var view = View.GONE

        if(reminderSet){
            view = View.VISIBLE
        }

        return view
    }

    private fun reminderTime(reminderInput : Date) : String {
        val format = SimpleDateFormat("EEE, dd/MM/yy")
        val date = format.format(reminderInput)
        return date.toString()
    }
}