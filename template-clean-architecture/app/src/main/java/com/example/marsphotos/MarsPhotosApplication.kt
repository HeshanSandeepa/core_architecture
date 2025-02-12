package com.example.marsphotos

import android.app.Application
import com.example.marsphotos.data.MarsDataRepository
import com.example.marsphotos.framework.NetworkMarsDataSource

class MarsPhotosApplication : Application() {

    lateinit var  marsDataRepository:MarsDataRepository
    override fun onCreate() {
        super.onCreate()
        marsDataRepository = MarsDataRepository(NetworkMarsDataSource())
    }
}