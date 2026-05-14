package com.example.eventmaster.repository.categoria

import com.example.eventmaster.ui.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getAllCategories(): Flow<List<Category>>
    suspend fun getCategoryById(id: Int): Category?
    suspend fun insertCategory(category: Category)
}
