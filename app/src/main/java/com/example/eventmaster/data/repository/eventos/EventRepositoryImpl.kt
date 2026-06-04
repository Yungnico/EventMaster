package com.example.eventmaster.data.repository.eventos

import com.example.eventmaster.data.local.AppDatabase
import com.example.eventmaster.data.local.entity.toDomain
import com.example.eventmaster.data.local.entity.toEntity
import com.example.eventmaster.data.remote.dto.toDomain
import com.example.eventmaster.data.remote.dto.toDto
import com.example.eventmaster.data.remote.service.EventApiService
import com.example.eventmaster.ui.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val apiService: EventApiService
) : EventRepository {

    override fun getAllEvents(): Flow<List<Event>> {
        return database.eventDao().getAllEvents()
            .map { entities -> entities.map { it.toDomain() } }
            .onStart {
                try {
                    val remoteEvents = apiService.getEventos()
                    database.eventDao().insertAll(remoteEvents.map { it.toDomain().toEntity() })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    override fun getEventsByCategory(categoryId: Int): Flow<List<Event>> {
        return database.eventDao().getEventsByCategory(categoryId)
            .map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun getEventById(id: Int): Event? {
        return database.eventDao().getEventById(id)?.toDomain()
    }

    override suspend fun insertEvent(event: Event) {
        try {
            val remoteDto = apiService.createEvento(event.toDto())
            database.eventDao().insertEvent(remoteDto.toDomain().toEntity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}