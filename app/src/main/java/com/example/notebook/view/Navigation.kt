package com.example.notebook.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notebook.model.entities.NoteData
import com.example.notebook.model.entities.NoteList
import com.example.notebook.viewmodel.HomeScreenViewModel

@Composable
fun Navigation(note: NoteList, vm: HomeScreenViewModel, delete: (NoteData) -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreen.routes) {
        composable(route = Routes.HomeScreen.routes) {
            HomeScreen(
                navController = navController,
                todoList = note,
                note = delete
            ) {
                if (it.isBlank()) {
                    // vm.updateScreen()
                } else {
                    // vm.search(it)
                }
            }
        }
        composable(route = Routes.AddScreen.routes) {
            AddTodoScreen(navController = navController) { title, body ->
                vm.addTodo(NoteData(title = title, body = body, completed = false))
            }
        }
        composable(
            route = Routes.EditScreen.routes + "/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.StringType
                nullable = false
            })
        ) { entry ->
            val index = entry.arguments?.getString("index")?.toInt()

            EditTodoScreen(
                navController = navController,
                editTodo = { title, body ->
                    if (index != null) {
                        vm.editNote(NoteData(title = title, body = body, completed = false), index)
                    }
                })
        }
    }
}
