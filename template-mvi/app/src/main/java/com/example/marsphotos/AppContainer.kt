package com.example.marsphotos

import com.example.marsphotos.data.repository.MarsPhotosRepository
import com.example.marsphotos.data.repository.NetworkMarsPhotosRepository
import com.example.marsphotos.network.MarsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository;
}

class DefaultAppContainer : AppContainer {
    private  val BASE_URL =
        "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }

}
