package com.example.todolist.data

import kotlinx.coroutines.flow.Flow

class OfflineRepository(private val toDoDao: ToDoDao): ListRepository {
    override fun getAllTasks() : Flow<List<Task>> = toDoDao.getAllTasks()
    override fun insertTask(task: Task) = toDoDao.insertTask(task)
    override fun deleteTask(task: Task) = toDoDao.deleteTask(task)

}