package com.example.latihan2.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.latihan2.R
import com.example.latihan2.TaskData.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(val taskList: ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTask = itemView.title_task
        val detailTask = itemView.detail_task
        val categoryTask = itemView.category_task
        val dateTask = itemView.date_task

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

        holder.titleTask.setText(taskList[position].title)
        holder.detailTask.setText(taskList[position].detail)
        holder.categoryTask.setText(taskList[position].category)
        holder.dateTask.setText(taskList[position].date.toString())

    }
}