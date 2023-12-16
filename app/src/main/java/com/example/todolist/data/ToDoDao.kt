package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Upsert
    fun insertTask(task: Task)
    @Delete
    fun deleteTask(task: Task)
    @Query("SELECT * FROM Task")
    fun getAllTasks() : Flow<List<Task>>
}