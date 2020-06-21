package com.example.latihan2.RoomDB.Dao

interface Dao<T> {
    fun getAll() : List<T>
    fun insert(t : T)
    fun update(t : T)
    fun delete(t : T)
}