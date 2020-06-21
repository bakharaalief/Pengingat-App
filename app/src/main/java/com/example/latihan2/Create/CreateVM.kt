package com.example.latihan2.Create

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.latihan2.RoomDB.AppDB
import com.example.latihan2.RoomDB.Entity.TaskData
import com.example.latihan2.TaskData.TaskInput
import java.util.*

class CreateVM(application: Application) : AndroidViewModel(application) {

    private val _taskData = MutableLiveData<TaskInput>()
    val taskData: LiveData<TaskInput>
        get() = _taskData

    var db : AppDB?

    init{
        db = AppDB.getDB(application)
    }

    fun insertData(title : String, detail : String, category : String){
        val data = TaskData(titleTask = title, detailTask = detail, categoryTask = category)
        db?.taskDao()?.insert(data)

        Log.i("wadaw", "Data berhasil ditambah")
    }



}