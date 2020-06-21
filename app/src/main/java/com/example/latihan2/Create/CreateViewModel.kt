package com.example.latihan2.Create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihan2.RoomDB.AppDB
import com.example.latihan2.RoomDB.Entity.TaskData
import com.example.latihan2.TaskData.TaskInput
import java.util.*

class CreateViewModel() : ViewModel() {

    private val _taskInput = MutableLiveData<TaskInput>()
    val taskInput : LiveData<TaskInput>
        get() = _taskInput

    init {

        val data = TaskInput("", "", "")
        _taskInput.value = data
        //db = AppDB.getDB()

    }

    fun inputData(title : String, detail : String, category : String){
        val data =
            TaskInput(title, detail, category)

        //val data2 = TaskData(titleTask = title, detailTask = detail, categoryTask = category, dateTask = Date())


        _taskInput.value = data
    }



}