package com.egarcia.employee.directory.data.remote.api

import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse
import retrofit2.http.GET

interface EmployeeApiService {
    //https://s3.amazonaws.com/sq-mobile-interview/employees.json
    @GET("employees.json")
    suspend fun getEmployees(): EmployeeListResponse

    //https://s3.amazonaws.com/sq-mobile-interview/employees_malformed.json
    @GET("employees_malformed.json")
    suspend fun getEmployeesMalformed(): EmployeeListResponse

    //https://s3.amazonaws.com/sq-mobile-interview/employees_empty.json
    @GET("employees_empty.json")
    suspend fun getEmployeesEmpty(): EmployeeListResponse
}