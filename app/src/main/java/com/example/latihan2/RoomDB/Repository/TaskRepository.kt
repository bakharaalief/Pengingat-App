package com.example.latihan2.RoomDB.Repository

import com.example.latihan2.RoomDB.Dao.TaskDao
import com.example.latihan2.RoomDB.Entity.TaskData

class TaskRepository (private val taskDao: TaskDao){

    suspend fun insertTask(taskData: TaskData){
        taskDao.insert(taskData)
    }

}