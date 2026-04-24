package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eventmaster.ui.model.Category
import com.example.eventmaster.ui.model.Event
import com.example.eventmaster.ui.state.CategoryFormState
import com.example.eventmaster.ui.state.EventFormState
import com.example.eventmaster.ui.state.EventState

class EventViewModel : ViewModel() {

    var state by mutableStateOf(EventState())
        private set

    var formState by mutableStateOf(EventFormState())
        private set

    var categoryFormState by mutableStateOf(CategoryFormState())
        private set

    private var nextEventId = 1
    private var nextCategoryId = 4


    fun onTitleChange(value: String) {
        formState = formState.copy(title = value, titleError = null)
    }

    fun onDescriptionChange(value: String) {
        formState = formState.copy(description = value, descriptionError = null)
    }

    fun onDateChange(value: String) {
        formState = formState.copy(date = value, dateError = null)
    }

    fun onLocationChange(value: String) {
        formState = formState.copy(location = value, locationError = null)
    }

    fun onCategorySelected(id: Int) {
        formState = formState.copy(categoryId = id, categoryError = null)
    }

    fun submitEvent(): Boolean {

        var hasError = false

        if (formState.title.isBlank()) {
            formState = formState.copy(titleError = "Campo obligatorio")
            hasError = true
        }

        if (formState.description.isBlank()) {
            formState = formState.copy(descriptionError = "Campo obligatorio")
            hasError = true
        }

        if (formState.date.isBlank()) {
            formState = formState.copy(dateError = "Campo obligatorio")
            hasError = true
        }
        val dateRegex = Regex("^\\d{2}/\\d{2}/\\d{4}$")

        if (!dateRegex.matches(formState.date)) {
            formState = formState.copy(
                dateError = "Formato inválido (dd/MM/yyyy)"
            )
            hasError = true
        }

        if (formState.location.isBlank()) {
            formState = formState.copy(locationError = "Campo obligatorio")
            hasError = true
        }

        if (formState.categoryId == null) {
            formState = formState.copy(categoryError = "Selecciona categoría")
            hasError = true
        }

        if (hasError) return false

        val newEvent = Event(
            id = nextEventId++,
            categoryId = formState.categoryId!!,
            title = formState.title,
            description = formState.description,
            date = formState.date,
            location = formState.location
        )

        state = state.copy(events = state.events + newEvent)
        formState = EventFormState()

        return true
    }
    fun onCategoryNameChange(value: String) {
        categoryFormState = categoryFormState.copy(
            name = value,
            nameError = null
        )
    }

    fun addCategory(): Boolean {

        if (categoryFormState.name.isBlank()) {
            categoryFormState = categoryFormState.copy(
                nameError = "Nombre obligatorio"
            )
            return false
        }

        val newCategory = Category(
            id = nextCategoryId++,
            name = categoryFormState.name
        )

        state = state.copy(
            categories = state.categories + newCategory
        )

        categoryFormState = CategoryFormState()

        return true
    }
}