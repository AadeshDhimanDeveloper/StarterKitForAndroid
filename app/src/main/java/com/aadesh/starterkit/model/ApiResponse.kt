package com.aadesh.starterkit.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
  @SerializedName("status")
  val status: String,
  @SerializedName("data")
  val employeeList: List<Employee>,
  @SerializedName("message")
  val message: String
)
