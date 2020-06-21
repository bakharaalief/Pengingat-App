package com.example.latihan2.RoomDB.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    val id_task : Long? = null,
    @ColumnInfo(name = "title_task")
    val titleTask : String?,
    @ColumnInfo(name = "detail_task")
    val detailTask : String?,
    @ColumnInfo(name = "category_task")
    val categoryTask : String?,
    @ColumnInfo(name = "date_task")
    val dateTask : Date? = null
)