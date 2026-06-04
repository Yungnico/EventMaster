package com.example.eventmaster.data.remote.service

import com.example.eventmaster.data.remote.dto.CategoryDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryApiService {
    @GET("categorias")
    suspend fun getCategorias(): List<CategoryDto>

    @POST("categorias")
    suspend fun createCategoria(@Body categoria: CategoryDto): CategoryDto
}