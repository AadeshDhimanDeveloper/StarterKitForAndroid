package com.aadesh.starterkit.repository

import com.aadesh.starterkit.model.ApiResponse
import com.aadesh.starterkit.retrofit.ApiResult
import com.aadesh.starterkit.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
  suspend fun getEmployees(): ApiResult<ApiResponse> {
    return withContext(Dispatchers.IO) {
      try {
        val response = apiService.getEmployees()
        ApiResult.Success(response)
      } catch (e: Exception) {
        ApiResult.Error(e)
      }
    }
  }
}
