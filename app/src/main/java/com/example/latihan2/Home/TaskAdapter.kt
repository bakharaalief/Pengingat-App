package com.example.latihan2.Home

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.latihan2.R
import com.example.latihan2.RoomDB.Entity.TaskData
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(val taskList: List<TaskData>, val viewModel: HomeVM) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {



    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTask = itemView.title_task
        val detailTask = itemView.detail_task
        val categoryTask = itemView.category_task
        val dateTask = itemView.date_task
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

        holder.titleTask.setText(taskList[position].titleTask)
        holder.detailTask.setText(taskList[position].detailTask)
        holder.categoryTask.setText(taskList[position].categoryTask)
        holder.dateTask.setText(taskList[position].dateTask.toString())
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

}