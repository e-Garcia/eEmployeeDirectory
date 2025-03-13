package com.egarcia.employee.directory.data.remote

import com.egarcia.employee.directory.data.remote.api.EmployeeApiService
import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse
import com.egarcia.employee.directory.data.remote.models.isValid

class EmployeeRemoteDataSourceImpl(private val apiService: EmployeeApiService) : EmployeeRemoteDataSource {
    override suspend fun getEmployees(): Result<EmployeeListResponse> {
        return try {
            val response = apiService.getEmployees()
            if (response.isValid()) {
                Result.success(response)
            } else {
                Result.failure(Exception(INVALID_RESPONSE_DESCRIPTION))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEmployeesMalformed(): Result<EmployeeListResponse> {
        return try {
            val response = apiService.getEmployeesMalformed()
            if (response.isValid()) {
                Result.success(response)
            } else {
                Result.failure(Exception(INVALID_RESPONSE_DESCRIPTION))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getEmployeesEmpty(): Result<EmployeeListResponse> {
        return try {
            val response = apiService.getEmployeesEmpty()
            if (response.isValid()) {
                Result.success(response)
            } else {
                Result.failure(Exception(INVALID_RESPONSE_DESCRIPTION))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}