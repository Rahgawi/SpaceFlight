package com.example.spaceflights.data.remote

import com.example.spaceflights.data.model.Launch
import com.example.spaceflights.data.model.LaunchResponse
import com.example.spaceflights.data.model.LocationResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL ="https://ll.thespacedevs.com/2.2.0/" // Basis URL Daten werden abgerufen

private  val logger: HttpLoggingInterceptor =HttpLoggingInterceptor().apply { //Protokollieren von HTTP-Anfragen und -Antworten
    level = HttpLoggingInterceptor.Level.BODY
}

private val httpClient = OkHttpClient.Builder()
    .addInterceptor(logger)
    .build()

// Moshi Bibliothek
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()) //Moshi konfiguriert (Datentypen und Klassen)
    .build()

private val retrofit = Retrofit.Builder() //Bibliothek die HTTP-Anfragen verwaltet, automatische Umwandlung von JSON in Kotlin Objekte
    .addConverterFactory(MoshiConverterFactory.create(moshi)) //Stellt sicher, dass Retrofit die JSON-Daten mithilfe von Moshi verarbeitet
    .baseUrl(BASE_URL)
    .client(httpClient) //HTTP-Anfragen
    .build()

interface ApiService{
    @GET("launch/upcoming")
    suspend fun getLaunches(@Query("limit") limit: Int = 10, @Query("offset") offset: Int = 0  ):  LaunchResponse

    @GET("location/")
    suspend fun getLocations():LocationResponse

}
object SpaceFlightsApi {
    val retrofitService:ApiService by lazy { retrofit.create(ApiService::class.java) }
}