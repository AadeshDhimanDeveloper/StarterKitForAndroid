package com.aadesh.starterkit.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.aadesh.starterkit.model.ApiResponse
import com.aadesh.starterkit.model.Employee
import com.aadesh.starterkit.repository.Repository
import com.aadesh.starterkit.retrofit.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
  private val _employees = MutableLiveData<ApiResult<ApiResponse>>()
  val employees: LiveData<ApiResult<ApiResponse>> get() = _employees

  fun getEmployees() {
    _employees.postValue(ApiResult.Loading) // Set loading state
    viewModelScope.launch {
      val result = repository.getEmployees()
      _employees.postValue(result)
    }
  }
}
