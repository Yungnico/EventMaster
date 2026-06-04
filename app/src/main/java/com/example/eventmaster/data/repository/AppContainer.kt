package com.example.eventmaster.data.repository

import android.content.Context
import com.example.eventmaster.data.local.AppDatabase
import com.example.eventmaster.data.repository.categoria.CategoryRepository
import com.example.eventmaster.data.repository.categoria.CategoryRepositoryImpl
import com.example.eventmaster.data.repository.eventos.EventRepository
import com.example.eventmaster.data.repository.eventos.EventRepositoryImpl
import com.example.eventmaster.di.NetworkModule

interface AppContainer {
    val categoryRepository: CategoryRepository
    val eventRepository: EventRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    private val database by lazy { AppDatabase.getDatabase(context) }

    private val retrofit by lazy { NetworkModule.provideRetrofit() }

    override val categoryRepository: CategoryRepository by lazy {
        CategoryRepositoryImpl(
            database,
            NetworkModule.provideCategoryApiService(retrofit)
        )
    }

    override val eventRepository: EventRepository by lazy {
        EventRepositoryImpl(
            database,
            NetworkModule.provideEventApiService(retrofit)
        )
    }
}
