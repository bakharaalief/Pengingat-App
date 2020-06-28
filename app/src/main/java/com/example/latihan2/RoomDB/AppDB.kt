package com.example.latihan2.RoomDB

import android.content.Context
import androidx.room.*
import com.example.latihan2.RoomDB.Dao.TaskDao
import com.example.latihan2.RoomDB.Entity.MIGRATION_1_2
import com.example.latihan2.RoomDB.Entity.TaskData

@Database(entities = [TaskData::class], version = 2)
@TypeConverters(Converters::class)

abstract class AppDB : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    companion object{

        /*singleton method*/
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDB(context: Context) : AppDB? {

            if(INSTANCE == null){

                synchronized(AppDB::class.java){
                    INSTANCE =
                        Room
                            .databaseBuilder(context.applicationContext, AppDB::class.java, "Task_DB")
                            .addMigrations(MIGRATION_1_2)
                            .build()
                }

            }

            return INSTANCE

        }

    }
}