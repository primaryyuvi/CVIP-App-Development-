package com.example.todolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Upsert
    suspend fun insertTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
    @Query("SELECT * FROM Task")
    fun getAllTasks() : Flow<List<Task>>
    @Query("SELECT * FROM Task WHERE id = :id")
    fun getTask(id : Int) : Flow<Task?>
}