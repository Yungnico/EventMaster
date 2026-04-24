package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eventmaster.ui.model.Category
import com.example.eventmaster.ui.model.Event
import com.example.eventmaster.ui.state.EventState

class EventViewModel : ViewModel() {

    var state by mutableStateOf(
        EventState(
            categories = listOf(
                Category(1, "Música"),
                Category(2, "Tecnología"),
                Category(3, "Deportes")
            )
        )
    )
        private set

    private var nextCategoryId = 4
    private var nextEventId = 1


    fun addCategory(name: String, description: String = "") {
        val newCategory = Category(
            id = nextCategoryId++,
            name = name,
            description = description
        )

        state = state.copy(
            categories = state.categories + newCategory
        )
    }


    fun addEvent(
        title: String,
        description: String,
        date: String,
        location: String,
        categoryId: Int,
        imageResName: String? = null
    ) {
        val newEvent = Event(
            id = nextEventId++,
            categoryId = categoryId,
            title = title,
            description = description,
            date = date,
            location = location,
            imageResName = imageResName
        )

        state = state.copy(
            events = state.events + newEvent
        )
    }

    fun getEventsByCategory(categoryId: Int): List<Event> {
        return state.events.filter { it.categoryId == categoryId }
    }

    fun getEventById(id: Int): Event? {
        return state.events.find { it.id == id }
    }
    fun validateAndAddEvent(
        title: String,
        description: String,
        date: String,
        location: String,
        categoryId: Int
    ): Pair<Boolean, Map<String, String>> {

        val errors = mutableMapOf<String, String>()

        if (title.isBlank()) {
            errors["title"] = "Título obligatorio"
        }

        if (description.isBlank()) {
            errors["description"] = "Descripción obligatoria"
        }

        if (location.isBlank()) {
            errors["location"] = "Ubicación obligatoria"
        }

        if (date.isBlank()) {
            errors["date"] = "Fecha obligatoria"
        } else {
            val regex = Regex("^\\d{2}/\\d{2}/\\d{4}$")

            if (!regex.matches(date)) {
                errors["date"] = "Formato debe ser dd/MM/yyyy"
            }
        }

        if (errors.isNotEmpty()) {
            return Pair(false, errors)
        }

        addEvent(
            title = title,
            description = description,
            date = date,
            location = location,
            categoryId = categoryId
        )

        return Pair(true, emptyMap())
    }
    fun validateAndAddCategory(
        name: String,
        description: String
    ): Pair<Boolean, String?> {

        if (name.isBlank()) {
            return Pair(false, "El nombre es obligatorio")
        }

        if (name.length < 3) {
            return Pair(false, "Mínimo 3 caracteres")
        }

        addCategory(name, description)

        return Pair(true, null)
    }
}