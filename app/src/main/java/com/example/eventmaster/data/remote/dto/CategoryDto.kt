package com.example.eventmaster.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.example.eventmaster.ui.model.Category

data class CategoryDto(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val name: String,
    @SerializedName("descripcion") val description: String?
)

fun CategoryDto.toDomain() = Category(
    id = this.id,
    name = this.name,
    description = this.description ?: ""
)

fun Category.toDto() = CategoryDto(
    id = this.id,
    name = this.name,
    description = this.description
)
