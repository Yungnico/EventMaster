package com.example.eventmaster.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eventmaster.local.dao.CategoryDao
import com.example.eventmaster.local.dao.EventDao
import com.example.eventmaster.local.entity.CategoryEntity
import com.example.eventmaster.local.entity.EventEntity

@Database(entities = [CategoryEntity::class, EventEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "App_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
