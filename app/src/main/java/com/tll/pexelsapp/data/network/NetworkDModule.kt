package com.tll.pexelsapp.data.network

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val KEY_AUTHORIZATION = "Authorization"
private const val AUTHORIZATION_TOKEN = "563492ad6f9170000100000172c80e246d6140d29af6505cc6a6a0ea"

@Module
object NetworkModule{
    @Provides
    fun createNetworkInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                .addHeader(KEY_AUTHORIZATION , AUTHORIZATION_TOKEN)
                .build()
            it.proceed(request)
        }
    }

    @Provides
    fun createOkHttpClient(
        networkInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(networkInterceptor)
            .hostnameVerifier { _, _ -> true }
            .retryOnConnectionFailure(false)
            .build()
    }

    @Provides
    inline fun <reified T> createApiService(okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(T::class.java)
    }
}



