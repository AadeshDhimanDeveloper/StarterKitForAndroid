package com.aadesh.starterkit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aadesh.starterkit.R
import com.aadesh.starterkit.databinding.ItemEmployeeBinding
import com.aadesh.starterkit.model.Employee
import com.bumptech.glide.Glide

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {
  private val employees = mutableListOf<Employee>()
  fun setEmployeeData(employeesList: List<Employee>) {
    employees.clear()
    employees.addAll(employeesList)
    notifyItemRangeChanged(0, employees.size)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
    val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return EmployeeViewHolder(binding)
  }

  override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
    holder.bind(employees[position])
  }

  override fun getItemCount(): Int {
    return employees.size
  }

  class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(employee: Employee) {
      Glide.with(binding.profileImage)
        .load(employee.profileImage)
        .into(binding.profileImage)
      binding.employeeName.text =
        binding.root.context.getString(R.string.employee_name, employee.employeeName)
      binding.employeeAge.text =
        binding.root.context.getString(R.string.employee_age, employee.employeeAge.toString())
      binding.employeeSalary.text =
        binding.root.context.getString(R.string.employee_salary, employee.employeeSalary.toString())
    }
  }
}
