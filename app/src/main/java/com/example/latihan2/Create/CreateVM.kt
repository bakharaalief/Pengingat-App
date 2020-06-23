package com.example.latihan2.Create

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.latihan2.RoomDB.AppDB
import com.example.latihan2.RoomDB.Entity.TaskData
import com.example.latihan2.RoomDB.Repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CreateVM(application: Application) : AndroidViewModel(application) {

    private val repository : TaskRepository

    var db : AppDB?

    init{
        db = AppDB.getDB(application)
        val taskDao = db!!.taskDao()
        repository = TaskRepository(taskDao)
    }

    fun insertData(title : String, detail : String, category : String) = viewModelScope.launch(Dispatchers.IO){

        val data = TaskData(titleTask = title, detailTask = detail, categoryTask = category, dateTask = Date())
        repository.insertTask(data)

        Log.i("wadaw", "Data berhasil ditambah")
    }



}