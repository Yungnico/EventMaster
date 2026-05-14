package com.example.eventmaster.repository.categoria

import com.example.eventmaster.local.AppDatabase
import com.example.eventmaster.local.entity.toDomain
import com.example.eventmaster.local.entity.toEntity
import com.example.eventmaster.ui.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val database: AppDatabase
) : CategoryRepository {
    override fun getAllCategories(): Flow<List<Category>> {
        return database.categoryDao().getAllCategories().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getCategoryById(id: Int): Category? {
        return database.categoryDao().getCategoryById(id)?.toDomain()
    }

    override suspend fun insertCategory(category: Category) {
        database.categoryDao().insertCategory(category.toEntity())
    }
}
