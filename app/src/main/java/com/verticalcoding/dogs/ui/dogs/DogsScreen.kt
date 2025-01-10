package com.verticalcoding.dogs.ui.dogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DogsScreen(modifier: Modifier = Modifier, viewModel: DogsViewModel = hiltViewModel()) {
    val items by viewModel.uiState.collectAsStateWithLifecycle()
    if (items is UiState.Success) {
        DogsTypeScreen(
            items = (items as UiState.Success).data,
            onSave = viewModel::addDog,
            modifier = modifier
        )
    }
}

@Composable
internal fun DogsTypeScreen(
    items: List<String>,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        var dog by remember { mutableStateOf("Czarek") }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = dog,
                onValueChange = { dog = it }
            )

            Button(modifier = Modifier.width(96.dp), onClick = { onSave(dog) }) {
                Text("Save")
            }
        }
        items.forEach {
            Text("$it")
        }
    }
}