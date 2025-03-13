package com.egarcia.employee.directory.data.remote

import com.egarcia.employee.directory.data.remote.api.EmployeeApiService
import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse

class EmployeeRemoteDataSourceImpl(private val apiService: EmployeeApiService) : EmployeeRemoteDataSource {
    override suspend fun getEmployees(): Result<EmployeeListResponse> {
        return try {
            Result.success(apiService.getEmployees())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEmployeesMalformed(): Result<EmployeeListResponse> {
        return try {
            Result.success(apiService.getEmployeesMalformed())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEmployeesEmpty(): Result<EmployeeListResponse> {
        return try {
            Result.success(apiService.getEmployeesEmpty())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}