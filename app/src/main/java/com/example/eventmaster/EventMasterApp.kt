package com.example.eventmaster

import android.app.Application
import com.example.eventmaster.data.repository.AppContainer
import com.example.eventmaster.data.repository.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EventMasterApp : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
