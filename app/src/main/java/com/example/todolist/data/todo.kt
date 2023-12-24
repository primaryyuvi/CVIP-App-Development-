package com.example.todolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val Id : Int,
    val title :String,
    val description : String
)
