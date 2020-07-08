package com.example.latihan2.Home

import android.content.Context
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.latihan2.R
import com.example.latihan2.RoomDB.Entity.TaskData
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_task.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class TaskAdapter(val viewModel: HomeVM, val context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var taskList: List<TaskData>? = null

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardTask = itemView.card_task
        val iconTask = itemView.icon_task
        val titleTask = itemView.title_task
        val detailTask = itemView.detail_task
        val categoryTask = itemView.category_task
        val dateTask = itemView.date_task
        val reminderTask = itemView.reminder_task
        val deleteButton = itemView.delete_task
        val completeButton = itemView.comple_task
        val uncompleteButton = itemView.uncomple_task

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val newView = inflater.inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(newView)
    }

    override fun getItemCount(): Int {
        return taskList!!.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val data = taskList!!.get(position)

        holder.cardTask.setCardBackgroundColor(
            ContextCompat.getColor(context, colorTask(data.colorTask!!))
        )
        holder.cardTask.setOnClickListener{ v ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
            v.findNavController().navigate(action)

        }
        holder.iconTask.setImageResource(iconView(data.categoryTask!!))
        holder.titleTask.setText(data.titleTask)
        holder.detailTask.setText(data.detailTask)
        holder.categoryTask.setText(data.categoryTask)
        holder.reminderTask.visibility = reminderView(data.reminderSet!!)
        holder.reminderTask.setText(reminderTime(data.reminderTask!!))
        holder.dateTask.setText(dateFormat(data.dateTask!!.time))
        holder.deleteButton.setOnClickListener { view ->
            deleteTask(data, view)
            notifyItemRemoved(position)
            notifyDataSetChanged()
        }

        /*complete button*/
        holder.completeButton.visibility =
            if(data.completeTask!!) View.GONE else View.VISIBLE
        holder.completeButton.setOnClickListener {
            completeTask(data)
            notifyItemChanged(position)
            notifyDataSetChanged()
        }

        /*uncomplete button*/
        holder.uncompleteButton.visibility = if(data.completeTask) View.VISIBLE else View.GONE
        holder.uncompleteButton.setOnClickListener {
            notifyItemChanged(position)
            uncompleteTask(data)
            notifyDataSetChanged()
        }

        /*striketrough line when task complete*/
        holder.titleTask.paintFlags =
            if(data.completeTask) holder.titleTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else holder.titleTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        holder.detailTask.paintFlags =
            if(data.completeTask) holder.titleTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else holder.titleTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        holder.categoryTask.paintFlags =
            if(data.completeTask) holder.titleTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else holder.titleTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }

    fun taskData(taskListInput : List<TaskData>){
        taskList = taskListInput
        notifyDataSetChanged()
    }

    private fun deleteTask(taskData: TaskData, view: View){
        val kata = "Yakin ingin menghapus ?"
        Snackbar.make(view, kata, Snackbar.LENGTH_LONG)
            .setAction("Ya"){
                viewModel.deleteTask(taskData)
            }
            .show()
    }
    private fun completeTask(taskData: TaskData){
        viewModel.updateTaskComplete(taskData.id_task!!, true)
    }

    private fun uncompleteTask(taskData: TaskData){
        viewModel.updateTaskComplete(taskData.id_task!!, false)
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

    private fun iconView(categoryInput : String) : Int{
        var icon = 0

        when(categoryInput){
            "Pekerjaan" -> icon = R.drawable.ic_work
            "Liburan" -> icon = R.drawable.ic_popcorn
            "Olahraga" -> icon = R.drawable.ic_fitness_center_black_24dp
        }

        return icon
    }
}