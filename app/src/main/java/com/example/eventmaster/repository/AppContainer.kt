package com.example.eventmaster.repository

import android.content.Context
import com.example.eventmaster.local.AppDatabase
import com.example.eventmaster.repository.categoria.CategoryRepository
import com.example.eventmaster.repository.categoria.CategoryRepositoryImpl
import com.example.eventmaster.repository.eventos.EventRepository
import com.example.eventmaster.repository.eventos.EventRepositoryImpl

interface AppContainer {
    val categoryRepository: CategoryRepository
    val eventRepository: EventRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val categoryRepository: CategoryRepository by lazy {
        CategoryRepositoryImpl(AppDatabase.getDatabase(context))
    }
    override val eventRepository: EventRepository by lazy {
        EventRepositoryImpl(AppDatabase.getDatabase(context))
    }
}
