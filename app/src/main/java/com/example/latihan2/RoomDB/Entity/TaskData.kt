package com.example.latihan2.RoomDB.Entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
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
    @ColumnInfo(name = "color_task") // version 2
    val colorTask : Int?,
    @ColumnInfo(name = "reminder_task_set") //version 2
    val reminderSet : Boolean?,
    @ColumnInfo(name = "reminder_task") //version 2
    val reminderTask : Date?,
    @ColumnInfo(name = "date_task")
    val dateTask : Date?,
    @ColumnInfo(name="complete_task")
    val completeTask : Boolean?
) : Parcelable

//Migration db v1 to v2
val MIGRATION_1_2 = object : Migration(1, 2) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(" Alter Table taskdata Add Column color_task INTEGER")
        database.execSQL(" Alter Table taskdata Add Column reminder_task_set BOOLEAN ")
        database.execSQL(" Alter Table taskdata Add Column reminder_task DATE")
    }

}

//Migration db v1 to v3
val MIGRATION_2_3 = object : Migration(2, 3) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(" Alter Table taskdata Add Column complete_task BOOLEAN")
    }

}

