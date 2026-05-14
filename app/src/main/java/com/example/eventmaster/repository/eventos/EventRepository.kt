package com.example.eventmaster.repository.eventos

import com.example.eventmaster.ui.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getAllEvents(): Flow<List<Event>>
    fun getEventsByCategory(categoryId: Int): Flow<List<Event>>
    suspend fun getEventById(id: Int): Event?
    suspend fun insertEvent(event: Event)
}
