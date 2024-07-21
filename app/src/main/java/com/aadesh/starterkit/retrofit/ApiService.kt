package com.aadesh.starterkit.retrofit

import com.aadesh.starterkit.model.ApiResponse
import retrofit2.http.GET

interface ApiService {
  @GET("api/v1/employees")
  suspend fun getEmployees(): ApiResponse
}
