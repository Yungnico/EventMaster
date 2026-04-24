package com.example.eventmaster.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.eventmaster.ui.viewmodel.EventViewModel

@Composable
fun AddCategoryScreen(
    navController: NavController,
    viewModel: EventViewModel
) {

    val form = viewModel.categoryFormState

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)
        .padding(16.dp)) {

        Text(stringResource(R.string.add_category))

        OutlinedTextField(
            value = form.name,
            onValueChange = { viewModel.onCategoryNameChange(it) },
            label = { Text(stringResource(R.string.name)) },
            isError = form.nameError != null
        )

        form.nameError?.let {
            Text(it, color = Color.Red)
        }

        Button(onClick = {
            val success = viewModel.addCategory()
            if (success) navController.popBackStack()
        }) {
            Text(stringResource(R.string.save))
        }
    }
}