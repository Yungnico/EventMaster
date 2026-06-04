package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventmaster.R
import com.example.eventmaster.data.repository.categoria.CategoryRepository
import com.example.eventmaster.data.repository.eventos.EventRepository
import com.example.eventmaster.ui.model.Category
import com.example.eventmaster.ui.model.Event
import com.example.eventmaster.ui.state.EventState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    var state by mutableStateOf(EventState(isLoading = true))
        private set

    init {
        viewModelScope.launch {
            combine(
                categoryRepository.getAllCategories(),
                eventRepository.getAllEvents()
            ) { categories, events ->
                EventState(
                    categories = categories,
                    events = events,
                    isLoading = false
                )
            }.collect { newState ->
                state = newState
            }
        }
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
                    id = 0,
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
    ): Pair<Boolean, Map<String, Int>> {

        val errors = mutableMapOf<String, Int>()

        if (title.isBlank()) {
            errors["title"] = R.string.error_required
        }

        if (description.isBlank()) {
            errors["description"] = R.string.error_required
        }

        if (location.isBlank()) {
            errors["location"] = R.string.error_required
        }

        if (date.isBlank()) {
            errors["date"] = R.string.error_required
        } else {
            val regex = Regex("^\\d{2}/\\d{2}/\\d{4}$")
            if (!regex.matches(date)) {
                errors["date"] = R.string.error_date_format
            }
        }

        if (errors.isNotEmpty()) {
            return Pair(false, errors)
        }

        addEvent(title, description, date, location, categoryId)
        return Pair(true, emptyMap())
    }

    fun validateAndAddCategory(
        name: String,
        description: String
    ): Pair<Boolean, Int?> {

        if (name.isBlank()) {
            return Pair(false, R.string.error_name_required)
        }

        if (name.length < 3) {
            return Pair(false, R.string.error_name_short)
        }

        addCategory(name, description)
        return Pair(true, null)
    }
}
