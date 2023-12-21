package com.example.todolist

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todolist.ui.TaskEditScreenViewModel
import com.example.todolist.ui.ToDoListViewModel

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            ToDoListViewModel(ToDoListApplication().container.listRepository)
        }
        initializer {
            TaskEditScreenViewModel(ToDoListApplication().container.listRepository)
        }
    }
}