package com.example.notebook.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notebook.R
import com.example.notebook.model.entities.NoteData
import com.example.notebook.model.entities.NoteList

@Composable
fun HomeScreen(
    navController: NavController,
    todoList: NoteList,
    note: (NoteData) -> Unit,
    searchFunction: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            NoteHeader("Notes", searchFunction = searchFunction)
            DynamicTitle("All Notes")
            StickyNotes(todoList, note) {
                navController.navigate(Routes.EditScreen.withArgs(it.toString()))
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(CircleShape),
            onClick = {
                navController.navigate(Routes.AddScreen.routes)
            },
            contentColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                "",
                tint = Color.Black,
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}
