package com.egarcia.employee.directory.data.remote.api

import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse
import retrofit2.http.GET

interface EmployeeApiService {
    //https://s3.amazonaws.com/sq-mobile-interview/employees.json
    @GET("employees.json")
    suspend fun getEmployees(): EmployeeListResponse
}