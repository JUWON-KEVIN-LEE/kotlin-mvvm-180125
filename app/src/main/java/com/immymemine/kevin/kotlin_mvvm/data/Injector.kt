package com.immymemine.kevin.kotlin_mvvm.data

import android.util.Log
import com.immymemine.kevin.kotlin_mvvm.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by quf93 on 2018-01-24.
 */
class Injector {
    val BASE_URL = "http://192.168.0.103:8079/"

    private fun provideRetrofit(baseUrl : String) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build()
    }

    private fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->  Log.d("JUWONLEE", message)})
        httpLoggingInterceptor.level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    fun provideApi() : InterfaceApi {
        return provideRetrofit(BASE_URL).create(InterfaceApi::class.java)
    }
}