package com.example.todolist.data

import android.app.Application
import android.content.Context

interface AppContainer {
    val listRepository: ListRepository
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val listRepository: ListRepository by lazy {
        OfflineRepository(ToDoListDatabase.getDatabase(context).toDoDao())
    }
}