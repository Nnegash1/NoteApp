package com.example.notebook.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notebook.R
import com.example.notebook.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(navController: NavController, addNote: (String, String) -> Unit) {
    var title by remember { mutableStateOf("Title ... ") }
    var body by remember { mutableStateOf("Body ... ") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp, shape = RoundedCornerShape(5.dp), brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Cyan,
                        Color.Black
                    )
                )
            )
    ) {
        Column(Modifier.background(Color.White)) {
            TextField(
                value = title,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    title = it
                },
                textStyle = Typography.headlineLarge,

                )

            TextField(
                value = body,
                modifier = Modifier.fillMaxSize(),
                onValueChange = {
                    body = it
                },
                textStyle = Typography.headlineLarge,
            )
        }
        FloatingActionButton(
            onClick = {
                addNote(title, body)
                navController.navigate(Routes.HomeScreen.routes)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(Color.Yellow)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                modifier = Modifier
                    .size(24.dp),
                tint = Color.Black,
                contentDescription = "Add to do"
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTodoScreen(
    oldTitle: String = "title",
    oldBody: String = "body",
    editTodo: (String, String) -> Unit,
    navController: NavController
) {
    var title by remember { mutableStateOf(oldTitle) }
    var body by remember { mutableStateOf(oldBody) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp, shape = RoundedCornerShape(5.dp), brush = Brush.linearGradient(
                    colors = listOf(
                        Color.Cyan,
                        Color.Black
                    )
                )
            )
    ) {
        Column(Modifier.background(Color.White)) {
            TextField(
                value = title,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    title = it
                },
                textStyle = Typography.headlineLarge,

                )

            TextField(
                value = body,
                modifier = Modifier.fillMaxSize(),
                onValueChange = {
                    body = it
                },
                textStyle = Typography.headlineLarge,
            )
        }

        IconButton(
            onClick = {
                editTodo(title, body)
                navController.navigate(Routes.HomeScreen.routes)

            },
            modifier = Modifier
                .align(Alignment.BottomEnd)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 10.dp),
                tint = Color.Black,
                contentDescription = "Add to do"
            )
        }
    }
}

