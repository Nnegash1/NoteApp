package com.example.notebook.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notebook.ui.theme.Typography

@Composable
fun SortToolBar() {
    val radioOption = listOf("By Title", "Order")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOption[1]) }
    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Title",
                modifier = Modifier.padding(start = 16.dp),
                style = Typography.headlineLarge,
                color = Color.White
            )
            RadioButton(
                selected = true,
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = "Date",
                modifier = Modifier,
                style = Typography.headlineLarge,
                color = Color.White
            )
            RadioButton(selected = false, onClick = { /*TODO*/ })
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ascending",
                modifier = Modifier.padding(start = 16.dp),
                style = Typography.headlineLarge,
                color = Color.White
            )
            RadioButton(
                selected = true,
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = "Descending",
                modifier = Modifier,
                style = Typography.headlineLarge,
                color = Color.White
            )
            RadioButton(selected = false, onClick = { /*TODO*/ })
        }
    }

}