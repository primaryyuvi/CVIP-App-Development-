package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todolist.data.AppApplication
import com.example.todolist.data.Task
import com.example.todolist.data.ToDoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ToDoListViewModel(toDoDao: ToDoDao) : ViewModel() {
    private val _state = MutableStateFlow(ToDoListState())
    val state = _state.asStateFlow()



}

data class ToDoListState(
    val tasks : Flow<List<Task>> = MutableStateFlow(listOf()),
    val taskInput : String = ""
)
