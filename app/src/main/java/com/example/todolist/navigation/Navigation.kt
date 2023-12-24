package com.example.todolist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.ui.TaskEditScreen
import com.example.todolist.ui.TaskEntryScreen
import com.example.todolist.ui.TasksLayout

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Destination.route
    ) {
        composable(Destination.route) {
            TasksLayout(
                onAddClick = {
                    navController.navigate(TaskEditScreen.route)
                },
                onTaskClick = {
                    navController.navigate("${TaskEditScreen.route}/${it}")
                }
            )
        }
        composable(TaskEditScreen.route) {
            TaskEntryScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "${TaskEditScreen.route}/{${TaskEditScreen.taskIdArg}}",
            arguments = listOf(navArgument(TaskEditScreen.taskIdArg) {
                type = NavType.IntType
            })
        ) {
            TaskEditScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}