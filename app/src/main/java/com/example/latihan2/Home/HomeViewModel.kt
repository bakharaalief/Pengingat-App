package com.example.latihan2.Home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihan2.TaskData.Task
import com.example.latihan2.TaskData.TaskInput
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel(val taskInput: TaskInput?) : ViewModel() {
    private var id : Long = 0
    private var title : String
    private var detail : String
    private var category : String

    private val listData : ArrayList<Task>
    private var _listTask = MutableLiveData<ArrayList<Task>>()
    val listTask: LiveData<ArrayList<Task>>
        get() = _listTask

    init {
        //for list data
        listData = ArrayList()
        _listTask.value = listData

        id = _listTask.value!!.size.toLong()
        title = ""
        detail = ""
        category = ""

        updateData()
    }

    fun updateData(){
        val task1 = Task(
            id++,
            "Jogging",
            "Jogging Santai Parah",
            "wadaw",
            Date()
        )
        val task2 = Task(
            id++,
            "Lari Pagi",
            "Lari Pagi agar Otot Kuat",
            "wadaw",
            Date()
        )

//        _listTask.value!!.add(task1)
//        _listTask.value!!.add(task2)

        val size = listTask.value!!.size

        Log.i("wadaw", "kepanggil update data")
        Log.i("wadaw", "${size}")

        if(taskInput == null){

            Log.i("wadaw", "nggak nambah nih")

        }

        else{

            title = taskInput.title
            detail = taskInput.detail
            category = taskInput.category

            val data =
                Task(
                    id++,
                    title,
                    detail,
                    category,
                    Date()
                )

            _listTask.value!!.add(data)
            Log.i("wadaw", "nambah nih")

        }

    }
}


