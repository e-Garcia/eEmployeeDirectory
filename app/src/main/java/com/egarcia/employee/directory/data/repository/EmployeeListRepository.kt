package com.egarcia.employee.directory.data.repository

import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse

interface EmployeeListRepository {
    suspend fun getEmployees(responseBehavior: ResponseBehavior): Result<EmployeeListResponse>
}