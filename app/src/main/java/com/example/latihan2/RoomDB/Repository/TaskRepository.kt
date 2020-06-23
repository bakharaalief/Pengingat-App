package com.example.latihan2.RoomDB.Repository

import androidx.lifecycle.LiveData
import com.example.latihan2.RoomDB.Dao.TaskDao
import com.example.latihan2.RoomDB.Entity.TaskData

class TaskRepository (private val taskDao: TaskDao){

    val allTask : LiveData<List<TaskData>> = taskDao.getAll()

    suspend fun insertTask(taskData: TaskData){
        taskDao.insert(taskData)
    }

    suspend fun deleteTask(taskData: TaskData){
        taskDao.delete(taskData)
    }


}