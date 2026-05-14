package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmaster.repository.categoria.CategoryRepository
import com.example.eventmaster.repository.eventos.EventRepository
import com.example.eventmaster.ui.model.Category
import com.example.eventmaster.ui.model.Event
import com.example.eventmaster.ui.state.EventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    var state by mutableStateOf(EventState())
        private set

    init {
        viewModelScope.launch {
            categoryRepository.getAllCategories().collectLatest { categories ->
                if (categories.isEmpty()) {
                    // Seed initial data if empty
                    seedData()
                } else {
                    state = state.copy(categories = categories)
                }
            }
        }
        viewModelScope.launch {
            eventRepository.getAllEvents().collectLatest { events ->
                state = state.copy(events = events)
            }
        }
    }

    private suspend fun seedData() {
        categoryRepository.insertCategory(Category(name = "Música", description = "Conciertos y festivales"))
        categoryRepository.insertCategory(Category(name = "Tecnología", description = "Conferencias y talleres"))
        categoryRepository.insertCategory(Category(name = "Deportes", description = "Torneos y competencias"))
    }

    fun addCategory(name: String, description: String = "") {
        viewModelScope.launch {
            categoryRepository.insertCategory(Category(name = name, description = description))
        }
    }

    fun addEvent(
        title: String,
        description: String,
        date: String,
        location: String,
        categoryId: Int,
        imageResName: String? = null
    ) {
        viewModelScope.launch {
            eventRepository.insertEvent(
                Event(
                    id = 0, // Room will generate ID
                    categoryId = categoryId,
                    title = title,
                    description = description,
                    date = date,
                    location = location,
                    imageResName = imageResName
                )
            )
        }
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
