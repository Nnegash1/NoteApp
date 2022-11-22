package com.example.notebook.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notebook.R
import com.example.notebook.model.entities.NoteData
import com.example.notebook.model.entities.NoteList
import com.example.notebook.ui.theme.CardYellow
import com.example.notebook.ui.theme.Typography


@Composable
fun NoteHeader(name: String, searchFunction: (String) -> Unit) {
    val visual = remember { mutableStateOf(false) }
    val search = remember { mutableStateOf(false) }
    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = Typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.padding(start = 10.dp)
            )
            Row {
                IconButton(onClick = {
                    visual.value = false
                    search.value = !search.value
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        tint = Color.White,
                        contentDescription = "Search",
                        modifier = Modifier
                            .size(35.dp)
                            .padding(end = 10.dp)
                    )
                }

                IconButton(onClick = {
                    visual.value = !visual.value
                    search.value = false
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.sort_svgrepo_com),
                        tint = Color.White,
                        contentDescription = "Sort",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = visual.value,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            SortToolBar()
        }

        AnimatedVisibility(
            visible = search.value,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Search(search = searchFunction)
        }
    }
}

@Composable
fun StickyNotes(
    notes: NoteList,
    delete: (NoteData) -> Unit,
    index: (Int) -> Unit
) {
    LazyColumn {
        itemsIndexed(notes.notes) { _, item ->
            Row(modifier = Modifier.clickable {
                index(item.id)
            }) {
                Note(item, delete)
            }
        }
    }
}

@Composable
fun Note(note: NoteData, delete: (NoteData) -> Unit) {
//    val random = Random().nextInt(3)
//    val backgroundColor = colorList[random]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(200.dp)
            .padding(16.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 5.dp,
                    bottomEnd = 5.dp,
                    topStart = 5.dp,
                    bottomStart = 5.dp
                )
            )
            .shadow(4.dp)
            .background(CardYellow)
    ) {

        Column() {
            Text(
                text = note.title,
                style = Typography.headlineLarge,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = note.body,
                style = Typography.bodyLarge,
                modifier = Modifier.padding(10.dp)
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = {
                Log.d("TAG", "Note: Clicked")
                delete(note)
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = "Delete",
                modifier = Modifier
                    .size(16.dp),
                tint = Color.Red
            )
        }
    }
}

@Composable
fun DynamicTitle(title: String) {
    Text(
        text = title,
        style = Typography.headlineLarge,
        color = Color.White,
        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(search: (String) -> Unit) {

    val searched = remember { mutableStateOf("") }
    search(searched.value)
    TextField(
        value = searched.value,
        onValueChange = {
            searched.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    )
}