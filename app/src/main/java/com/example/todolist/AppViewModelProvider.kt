package com.example.todolist

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todolist.ui.TaskEditScreenViewModel
import com.example.todolist.ui.TaskEditViewModel
import com.example.todolist.ui.ToDoListViewModel

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            ToDoListViewModel(toDoListApplication().container.listRepository)
        }
        initializer {
            TaskEditScreenViewModel(
                toDoListApplication().container.listRepository,
                this.createSavedStateHandle()
            )
        }
        initializer {
            TaskEditViewModel(
                toDoListApplication().container.listRepository,
                this.createSavedStateHandle()
            )
        }
    }
}

fun CreationExtras.toDoListApplication(): ToDoListApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ToDoListApplication)