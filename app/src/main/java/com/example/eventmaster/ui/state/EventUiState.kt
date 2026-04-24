package com.example.eventmaster.ui.state

import com.example.eventmaster.ui.model.Category
import com.example.eventmaster.ui.model.Event

data class EventState(
    val events: List<Event> = emptyList(),
    val categories: List<Category> = emptyList()
)

data class EventFormState(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val location: String = "",
    val categoryId: Int? = null,

    val titleError: String? = null,
    val descriptionError: String? = null,
    val dateError: String? = null,
    val locationError: String? = null,
    val categoryError: String? = null
)

data class CategoryFormState(
    val name: String = "",
    val nameError: String? = null
)