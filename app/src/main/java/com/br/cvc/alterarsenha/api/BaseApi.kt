package com.br.cvc.alterarsenha.api

import com.google.gson.GsonBuilder

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseApi {

    val BASE_URL = "https://us-central1-last-minute-app-71f82.cloudfunctions.net"
    private var retrofit: Retrofit? = null
    internal var gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

    val client: Retrofit?
        get() {

            if (retrofit == null) {

                val okClient = OkHttpClient.Builder()
                        .addNetworkInterceptor { chain ->
                            val request = chain.request().newBuilder().build()
                            chain.proceed(request)
                        }
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .build()
                retrofit = Retrofit.Builder()
                        .client(okClient)
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return retrofit
        }

    fun ClearClient() {
        retrofit = null
    }
}
