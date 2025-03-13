package com.egarcia.employee.directory.data.remote.api

import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse
import retrofit2.http.GET

interface EmployeeApiService {
    @GET("employees.json")
    suspend fun getEmployees(): EmployeeListResponse

    @GET("employees_malformed.json")
    suspend fun getEmployeesMalformed(): EmployeeListResponse

    @GET("employees_empty.json")
    suspend fun getEmployeesEmpty(): EmployeeListResponse
}