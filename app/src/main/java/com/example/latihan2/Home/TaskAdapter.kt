package com.example.latihan2.Home

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.latihan2.R
import com.example.latihan2.RoomDB.Entity.TaskData
import kotlinx.android.synthetic.main.item_task.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TaskAdapter(val taskList: List<TaskData>, val viewModel: HomeVM, val context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {



    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val colorTask = itemView.card_task
        val titleTask = itemView.title_task
        val detailTask = itemView.detail_task
        val categoryTask = itemView.category_task
        val dateTask = itemView.date_task
        val reminderTask = itemView.reminder_task
        val deleteButton = itemView.delete_task
        val completeButton = itemView.comple_task

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val newView = inflater.inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(newView)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val data = taskList[position]

        holder.colorTask.setCardBackgroundColor(
            ContextCompat.getColor(context, colorTask(data.colorTask!!))
        )
        holder.titleTask.setText(data.titleTask)
        holder.detailTask.setText(data.detailTask)
        holder.categoryTask.setText(data.categoryTask)
        holder.reminderTask.visibility = reminderView(data.reminderSet!!)
        holder.reminderTask.setText(reminderTime(data.reminderTask!!))
        holder.dateTask.setText(dateFormat(data.dateTask!!.time))
        holder.deleteButton.setOnClickListener {
            viewModel.deleteTask(taskList[position])
        }
        holder.completeButton.setOnClickListener {
            completeTask(holder)
        }

    }

    private fun completeTask(holder: TaskViewHolder){

        when(holder.completeButton.text){
            "complete" -> {

                holder.titleTask.paintFlags = holder.titleTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.detailTask.paintFlags = holder.detailTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.categoryTask.paintFlags = holder.categoryTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.dateTask.paintFlags = holder.dateTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.completeButton.text = "uncomplete"
            }


            "uncomplete" -> {

                holder.titleTask.paintFlags = holder.titleTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                holder.detailTask.paintFlags = holder.detailTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                holder.categoryTask.paintFlags = holder.categoryTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                holder.dateTask.paintFlags = holder.dateTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                holder.completeButton.text = "complete"

            }
        }

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
            1 -> data = android.R.color.holo_red_dark

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