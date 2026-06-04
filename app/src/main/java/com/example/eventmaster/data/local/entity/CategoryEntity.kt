package com.example.eventmaster.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eventmaster.ui.model.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String
)

fun CategoryEntity.toDomain() = Category(
    id = this.id,
    name = this.name,
    description = this.description
)

fun Category.toEntity() = CategoryEntity(
    id = this.id,
    name = this.name,
    description = this.description
)
