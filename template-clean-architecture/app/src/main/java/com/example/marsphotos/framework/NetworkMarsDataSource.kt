package com.example.marsphotos.framework

import com.example.marsphotos.data.MarsDataSource
import com.example.marsphotos.domain.MarsPhoto
import com.example.marsphotos.network.MarsApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkMarsDataSource: MarsDataSource {

    private  val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    override suspend fun getMarsPhotos(): Flow<List<MarsPhoto>> =
        flow {
            val photos = retrofit.create(MarsApiService::class.java).getPhotos()
            emit(photos)
        }
}