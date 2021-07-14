package com.example.myrooms.api

import com.example.myrooms.model.RoomsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("App/json/{page}.json")
    suspend fun fetchRooms(@Path("page") page: Int) : ApiResponse<RoomsResponse>

}