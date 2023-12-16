package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun getAllTasks(): Flow<List<Task>>
    fun insertTask(task: Task)
    fun deleteTask(task: Task)
}