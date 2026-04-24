package com.example.eventmaster.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eventmaster.R
import com.example.eventmaster.ui.components.CategoryDropdown
import com.example.eventmaster.ui.viewmodel.EventViewModel

@Composable
fun AddEventScreen(navController: NavController, viewModel: EventViewModel) {

    val form = viewModel.formState
    val categories = viewModel.state.categories

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)
        .padding(16.dp)) {

        OutlinedTextField(
            value = form.title,
            onValueChange = { viewModel.onTitleChange(it) },
            label = { Text(stringResource(R.string.titulo),color = MaterialTheme.colorScheme.onSurfaceVariant) },
            isError = form.titleError != null
        )
        form.titleError?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = form.description,
            onValueChange = { viewModel.onDescriptionChange(it) },
            label = { Text(stringResource(R.string.description),color = MaterialTheme.colorScheme.onSurfaceVariant) },
            isError = form.descriptionError != null
        )
        form.descriptionError?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = form.date,
            onValueChange = { viewModel.onDateChange(it) },
            label = { Text(stringResource(R.string.date),color = MaterialTheme.colorScheme.onSurfaceVariant) },
            isError = form.dateError != null
        )
        form.dateError?.let { Text(it, color = Color.Red) }

        OutlinedTextField(
            value = form.location,
            onValueChange = { viewModel.onLocationChange(it) },
            label = { Text(stringResource(R.string.ubication),color = MaterialTheme.colorScheme.onSurfaceVariant) },
            isError = form.locationError != null
        )
        form.locationError?.let { Text(it, color = Color.Red) }

        Spacer(modifier = Modifier.height(16.dp))

        CategoryDropdown(
            categories = categories,
            selectedId = form.categoryId,
            onCategorySelected = { viewModel.onCategorySelected(it) }
        )
        form.categoryError?.let {
            Text(it, color = Color.Red)
        }
        IconButton(onClick = {
            navController.navigate("add_category")
        }) {
            Icon(Icons.Default.Add, contentDescription = null)

        }

        form.categoryError?.let { Text(it, color = Color.Red) }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val success = viewModel.submitEvent()
            if (success) navController.popBackStack()
        }) {
            Text(stringResource(R.string.save))
        }
    }
}