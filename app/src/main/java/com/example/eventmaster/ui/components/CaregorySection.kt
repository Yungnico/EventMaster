package com.example.eventmaster.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventmaster.ui.model.Category
import com.example.eventmaster.ui.model.Event

@Composable
fun CategorySection(
    category: Category,
    events: List<Event>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = category.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )

        if (events.isEmpty()) {
            Text(
                text = "Sin eventos",
                modifier = Modifier.padding(start = 8.dp)
            )
        } else {
            events.forEach { event ->
                EventCard(
                    event = event,
                    categoryName = category.name
                )
            }
        }
    }
}