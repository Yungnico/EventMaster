package com.example.eventmaster.data.remote.service

import com.example.eventmaster.data.remote.dto.EventDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApiService {
    @GET("eventos")
    suspend fun getEventos(): List<EventDto>

    @POST("eventos")
    suspend fun createEvento(@Body evento: EventDto): EventDto
}