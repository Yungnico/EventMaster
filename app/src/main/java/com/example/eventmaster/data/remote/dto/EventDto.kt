package com.example.eventmaster.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.example.eventmaster.ui.model.Event

data class EventDto(
    @SerializedName("id") val id: Int,
    @SerializedName("categoria_id") val categoryId: Int,
    @SerializedName("titulo") val title: String,
    @SerializedName("descripcion") val description: String?,
    @SerializedName("fecha") val date: String,
    @SerializedName("localizacion") val location: String?,
    @SerializedName("url_imagen") val imageResName: String?
)

fun EventDto.toDomain() = Event(
    id = this.id,
    categoryId = this.categoryId,
    title = this.title,
    description = this.description ?: "",
    date = this.date,
    location = this.location ?: "",
    imageResName = this.imageResName
)

fun Event.toDto() = EventDto(
    id = this.id,
    categoryId = this.categoryId,
    title = this.title,
    description = this.description,
    date = this.date,
    location = this.location,
    imageResName = this.imageResName
)