package com.example.eventmaster.ui.model

data class Event(
    val id: Int,
    val categoryId: Int,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val imageResName: String? = null
)

data class Category(
    val id: Int,
    val name: String,
    val description: String = ""
)