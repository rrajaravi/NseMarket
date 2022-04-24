package com.example.nsemarket.rest

import com.example.nsemarket.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object NSEApi {
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val UA = "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_3_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36" // Get android user agent.


    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.NSE_API_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    private val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addNetworkInterceptor(logging)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalRequest = chain.request()
                    val newRequest = originalRequest.newBuilder()
                            .header("User-Agent", UA)
                            .build()
                    return chain.proceed(newRequest)
                }
            })

    val retrofitService: NSEApiService by lazy {
        retrofit().create(NSEApiService::class.java)
    }
}