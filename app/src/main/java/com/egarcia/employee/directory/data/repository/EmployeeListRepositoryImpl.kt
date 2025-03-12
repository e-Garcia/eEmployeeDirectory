package com.egarcia.employee.directory.data.repository

import com.egarcia.employee.directory.data.remote.EmployeeRemoteDataSource
import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse
import javax.inject.Inject

class EmployeeListRepositoryImpl @Inject constructor(private val remoteDataSource: EmployeeRemoteDataSource) :
    EmployeeListRepository {
    override suspend fun getEmployees(): Result<EmployeeListResponse> =
        remoteDataSource.getEmployees()
}