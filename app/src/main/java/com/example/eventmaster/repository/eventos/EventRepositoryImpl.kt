package com.example.eventmaster.repository.eventos

import com.example.eventmaster.local.AppDatabase
import com.example.eventmaster.local.entity.toDomain
import com.example.eventmaster.local.entity.toEntity
import com.example.eventmaster.ui.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : EventRepository {
    override fun getAllEvents(): Flow<List<Event>> {
        return database.eventDao().getAllEvents().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getEventsByCategory(categoryId: Int): Flow<List<Event>> {
        return database.eventDao().getEventsByCategory(categoryId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getEventById(id: Int): Event? {
        return database.eventDao().getEventById(id)?.toDomain()
    }

    override suspend fun insertEvent(event: Event) {
        database.eventDao().insertEvent(event.toEntity())
    }
}
