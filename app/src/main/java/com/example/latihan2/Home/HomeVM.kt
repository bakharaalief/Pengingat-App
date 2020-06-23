package com.example.latihan2.Home

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.latihan2.RoomDB.AppDB
import com.example.latihan2.RoomDB.Entity.TaskData
import com.example.latihan2.RoomDB.Repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVM(application: Application) : AndroidViewModel(application){

    val listTask : LiveData<List<TaskData>>
    private val repository : TaskRepository
    private val db : AppDB?

    init {
        db = AppDB.getDB(application)
        val taskDao = db!!.taskDao()
        repository = TaskRepository(taskDao)
        listTask = repository.allTask
    }

    fun deleteTask(taskData: TaskData) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteTask(taskData)
    }
}