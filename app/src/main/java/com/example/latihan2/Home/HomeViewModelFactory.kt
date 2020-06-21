package com.example.latihan2.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihan2.TaskData.TaskInput

class HomeViewModelFactory (val taskInput: TaskInput?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(taskInput) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}