package com.example.eventmaster.ui.state

import com.example.eventmaster.ui.model.Category
import com.example.eventmaster.ui.model.Event

data class EventState(
    val events: List<Event> = emptyList(),
    val categories: List<Category> = emptyList()
)
