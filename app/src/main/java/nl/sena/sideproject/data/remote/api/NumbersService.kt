package nl.sena.sideproject.data.remote.api

import nl.sena.sideproject.data.remote.response.Numbers
import retrofit2.http.GET

interface NumbersService {
    @GET("/numbers")
    suspend fun getNumbers(): Numbers
}