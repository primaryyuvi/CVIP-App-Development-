package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class OfflineRepository(private val toDoDao: ToDoDao): ListRepository {
    override fun getAllTasks() : Flow<List<Task>> = toDoDao.getAllTasks()
    override suspend fun insertTask(task: Task) = toDoDao.insertTask(task)
    override suspend fun deleteTask(task: Task) = toDoDao.deleteTask(task)
    override fun getItemStream(itemId: Int): Flow<Task?> = toDoDao.getTask(itemId)
}