package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todolist.ToDoListApplication
import com.example.todolist.data.ListRepository
import com.example.todolist.data.Task
import com.example.todolist.data.ToDoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToDoListViewModel(private val listRepository: ListRepository ) : ViewModel() {
    companion object{
        private const val TIME = 5000L
    }

    val toDoListState: StateFlow<ToDoListState> =
        listRepository.getAllTasks().map { ToDoListState() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIME),
                initialValue = ToDoListState()
            )
    fun deleteItem(task: Task) {
        viewModelScope.launch {
            listRepository.deleteTask(task)
        }
    }
}

data class ToDoListState(
    val tasks : List<Task> = listOf()
)
