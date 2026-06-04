package com.example.eventmaster.data.repository.categoria

import com.example.eventmaster.data.local.AppDatabase
import com.example.eventmaster.data.local.entity.toDomain
import com.example.eventmaster.data.local.entity.toEntity
import com.example.eventmaster.data.remote.dto.toDomain
import com.example.eventmaster.data.remote.dto.toDto
import com.example.eventmaster.data.remote.service.CategoryApiService
import com.example.eventmaster.ui.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val apiService: CategoryApiService
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> {
        return database.categoryDao().getAllCategories()
            .map { entities -> entities.map { it.toDomain() } }
            .onStart {
                try {
                    val remoteCategories = apiService.getCategorias()
                    database.categoryDao().insertAll(remoteCategories.map { it.toDomain().toEntity() })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
    }

    override suspend fun getCategoryById(id: Int): Category? {
        return database.categoryDao().getCategoryById(id)?.toDomain()
    }

    override suspend fun insertCategory(category: Category) {
        try {
            val remoteDto = apiService.createCategoria(category.toDto())
            database.categoryDao().insertCategory(remoteDto.toDomain().toEntity())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
