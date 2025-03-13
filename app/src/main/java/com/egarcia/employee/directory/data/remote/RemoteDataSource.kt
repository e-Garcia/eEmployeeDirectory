package com.egarcia.employee.directory.data.remote

import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse

interface EmployeeRemoteDataSource {
    suspend fun getEmployees(): Result<EmployeeListResponse>
    suspend fun getEmployeesMalformed(): Result<EmployeeListResponse>
    suspend fun getEmployeesEmpty(): Result<EmployeeListResponse>
}

