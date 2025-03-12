package com.egarcia.employee.directory.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.egarcia.employee.directory.domain.models.Employee

class EmployeeDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}