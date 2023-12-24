package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
     fun getItemStream(itemId: Int): Flow<Task?>
}