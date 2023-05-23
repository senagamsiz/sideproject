package nl.sena.sideproject.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import nl.sena.sideproject.data.remote.MockInterceptor
import nl.sena.sideproject.data.remote.api.NumbersService
import nl.sena.sideproject.data.remote.repository.NumbersRepository
import nl.sena.sideproject.data.remote.repository.NumbersRepositoryImpl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    factory { MockInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideNumbersService(get()) }
    single { provideRetrofit(get()) }
    single<NumbersRepository> { NumbersRepositoryImpl(get()) }
}

val contentType: MediaType = MediaType.get("application/json")

@OptIn(ExperimentalSerializationApi::class)
private val json = Json {
    ignoreUnknownKeys = true
    explicitNulls = false
    isLenient = true
}

@OptIn(ExperimentalSerializationApi::class)
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://example.com").client(okHttpClient)
        .addConverterFactory(
            json.asConverterFactory(contentType)
        ).build()
}

private fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(mockInterceptor).build()
}

private fun provideNumbersService(retrofit: Retrofit): NumbersService =
    retrofit.create(NumbersService::class.java)