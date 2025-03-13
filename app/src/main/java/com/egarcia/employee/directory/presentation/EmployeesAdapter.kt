package com.egarcia.employee.directory.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.egarcia.employee.R
import com.egarcia.employee.databinding.ItemEmployeeBinding
import com.egarcia.employee.directory.domain.models.Employee
import com.egarcia.employee.directory.presentation.util.EmployeeDiffCallback

class EmployeesAdapter : ListAdapter<Employee, EmployeesAdapter.EmployeeViewHolder>(
    EmployeeDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding =
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }


    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: Employee) {
            with(binding) {
                tvDirectoryName.text = employee.fullName
                tvDirectoryTeam.text = employee.team
                Glide.with(binding.root)
                    .load(employee.photoUrlSmall)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fallback(R.drawable.ic_fallback)
                    .error(R.drawable.ic_error)
                    .into(ivDirectoryPhoto)
            }
        }
    }
}

