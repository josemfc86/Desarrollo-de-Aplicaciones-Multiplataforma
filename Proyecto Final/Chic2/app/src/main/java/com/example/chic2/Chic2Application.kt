package com.example.chic2

import android.app.Application
import com.example.chic2.data.AppContainer
import com.example.chic2.data.DefaultAppContainer

class Chic2Application: Application() {
    // AppContainer instancia usada por el resto de clases para obtener dependencias
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}