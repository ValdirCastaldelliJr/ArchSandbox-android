package com.castaldelli.archsandbox.repository

import com.castaldelli.archsandbox.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Service {

    private const val TIMEOUT_IN_SEC: Long = 60

    @Synchronized fun create(): Api {
        val retrofit = Retrofit.Builder()
            .client(getDefaultClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL) // Url base é configurada no gradle.properties em tempo de compilação (segurança)
            .build()

        return retrofit.create(Api::class.java)
    }

    // configuração de client HTTP
    private fun getDefaultClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
            .addInterceptor { chain -> chain.proceed(configCommonHeader(chain)) }
            .addInterceptor(configDebugLog())
            .build()

    }

    // Colocar aqui Headers comuns a todas as chamadas //
    private fun configCommonHeader(chain: Interceptor.Chain): Request {
        return with(chain.request().newBuilder()) {
            header("Content-Type", "application/json") // <- Exemplo, mas aqui pode ser incluido o Token por exemplo
            build()
        }
    }

    // Logica de logs de servico para dev de acordo com o tipo de build
    private fun configDebugLog(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}