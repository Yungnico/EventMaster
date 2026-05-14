package com.example.eventmaster.di

import com.example.eventmaster.repository.categoria.CategoryRepository
import com.example.eventmaster.repository.categoria.CategoryRepositoryImpl
import com.example.eventmaster.repository.eventos.EventRepository
import com.example.eventmaster.repository.eventos.EventRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindEventRepository(
        eventRepositoryImpl: EventRepositoryImpl
    ): EventRepository
}
