package com.example.eventmaster.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.eventmaster.ui.model.Event

@Entity(
    tableName = "events",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["categoryId"])]
)
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val categoryId: Int,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val imageResName: String? = null
)

fun EventEntity.toDomain() = Event(
    id = this.id,
    categoryId = this.categoryId,
    title = this.title,
    description = this.description,
    date = this.date,
    location = this.location,
    imageResName = this.imageResName
)

fun Event.toEntity() = EventEntity(
    id = this.id,
    categoryId = this.categoryId,
    title = this.title,
    description = this.description,
    date = this.date,
    location = this.location,
    imageResName = this.imageResName
)
