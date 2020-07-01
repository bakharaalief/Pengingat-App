package com.example.latihan2.Home

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _listCategory = MutableLiveData<ArrayList<String>>()
    val listCategory : LiveData<ArrayList<String>>
        get() = _listCategory

    init {

        db = AppDB.getDB(application)
        val taskDao = db!!.taskDao()
        repository = TaskRepository(taskDao)
        listTask = repository.allTask

        _listCategory.value = ArrayList()

    }

    fun deleteTask(taskData: TaskData) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteTask(taskData)
    }

    fun updateTask(id : Long, taskData: TaskData) = viewModelScope.launch(Dispatchers.IO){

    }

    fun updateTaskComplete(id : Long, complete: Boolean) = viewModelScope.launch(Dispatchers.IO){
        repository.updateComplate(id, complete)
    }

    /*masih bug*/
//    fun categoryTask(listTask2 : List<TaskData>){
//        _listCategory.value!!.clear()
//        var counter = 0
//
//        for(data in listTask2){
//            val category = data.categoryTask
//            var found = false
//
////            Log.i("wadaw", "${category} yang masuk")
//
//            if(counter > 0){
//
//                for(data2 in _listCategory.value!!){
//
//                    when(data2.toLowerCase()){
//
//                        category!!.toLowerCase() -> found = true
//
//                        else -> found = false
//
//                    }
//
//                    if(!found){
//                        _listCategory.value!!.add(category)
////                        Log.i("wadaw", "${category} karena nggak ada")
//                    }
//
//                }
//
//                counter++
//            }
//
//            else{
//                _listCategory.value!!.add(category!!)
////                Log.i("wadaw", "${category} pertama")
//                counter++
//            }
//        }
//
//    }
}