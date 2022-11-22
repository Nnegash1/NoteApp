package com.example.notebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notebook.ui.theme.NoteBookTheme
import com.example.notebook.view.Navigation
import com.example.notebook.viewmodel.HomeNoteFactory
import com.example.notebook.viewmodel.HomeScreenViewModel
import com.example.notebook.viewmodel.states.UIStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: HomeNoteFactory
    private val vm: HomeScreenViewModel by viewModels { factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mockData = vm.notes.collectAsState()
            NoteBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    mockData.value.let { uiStatus ->
                        if (uiStatus is UIStatus.Notes) {
                            Navigation(uiStatus.notes, vm) {
                                vm.removeNote(it)
                            }
                        }
                    }
                    vm.getNotes()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteBookTheme {
        //Navigation(note = mockData)
    }
}