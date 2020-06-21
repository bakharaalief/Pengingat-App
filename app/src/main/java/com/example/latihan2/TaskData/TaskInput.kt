package com.example.latihan2.TaskData

import android.os.Parcelable
import android.text.Editable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskInput(val title: String, val detail: String, val category: String) : Parcelable{}