package com.example.latihan2.RoomDB.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.latihan2.RoomDB.Entity.TaskData

@Dao
interface TaskDao {

    @Query("SELECT * From taskdata")
    fun getAll(): LiveData<List<TaskData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taskData: TaskData)

    @Update
    fun update(taskData: TaskData)

    @Query("UPDATE taskdata SET complete_task= :complete WHERE id_task= :id ")
    fun updateComplete(id : Long, complete:Boolean)

    @Delete
    fun delete(taskData: TaskData)

}