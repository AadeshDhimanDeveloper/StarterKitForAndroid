package com.aadesh.starterkit.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aadesh.starterkit.adapter.EmployeeAdapter
import com.aadesh.starterkit.databinding.FragmentHomeBinding
import com.aadesh.starterkit.retrofit.ApiResult
import com.aadesh.starterkit.utils.SharedPreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

  private var _binding: FragmentHomeBinding? = null
  private val binding get() = _binding!!
  private val homeViewModel: HomeViewModel by viewModels()
  private lateinit var employeeAdapter: EmployeeAdapter

  @Inject
  lateinit var sharedPreferenceUtil: SharedPreferenceUtil

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    setupRecyclerView()
    observeViewModel()
    homeViewModel.getEmployees()
    return binding.root
  }

  /**
   * Set up the RecyclerView with the EmployeeAdapter and a LinearLayoutManager.
   */
  private fun setupRecyclerView() {
    employeeAdapter = EmployeeAdapter()
    binding.employeeRecyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = employeeAdapter
    }
  }

  /**
   * Observe the ViewModel's LiveData and update the adapter when data changes.
   */
  private fun observeViewModel() {
    homeViewModel.employees.observe(viewLifecycleOwner) { result ->
      when (result) {
        is ApiResult.Loading -> {
          binding.progressBar.visibility = View.VISIBLE
          binding.employeeRecyclerView.visibility = View.GONE
        }

        is ApiResult.Success -> {
          binding.progressBar.visibility = View.GONE
          binding.employeeRecyclerView.visibility = View.VISIBLE
          employeeAdapter.setEmployeeData(result.data.employeeList)
        }

        is ApiResult.Error -> {
          binding.progressBar.visibility = View.GONE
          binding.employeeRecyclerView.visibility = View.GONE
          Toast.makeText(context, result.exception.message, Toast.LENGTH_LONG).show()
        }
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
