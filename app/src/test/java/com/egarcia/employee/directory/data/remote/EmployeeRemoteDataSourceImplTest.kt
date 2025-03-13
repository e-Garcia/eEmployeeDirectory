package com.egarcia.employee.directory.data.remote

import com.egarcia.employee.directory.data.remote.api.EmployeeApiService
import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class EmployeeRemoteDataSourceImplTest {

    private lateinit var apiService: EmployeeApiService
    private lateinit var dataSource: EmployeeRemoteDataSourceImpl

    @Before
    fun setup() {
        apiService = mockk()
        dataSource = EmployeeRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `getEmployees, when api call is successful, it returns success result`() = runTest {
        // Given
        val expectedResponse = EmployeeListResponse(arrayListOf())
        coEvery { apiService.getEmployees() } returns expectedResponse

        // When
        val result = dataSource.getEmployees()

        // Then
        assertEquals(Result.success(expectedResponse), result)
    }

    @Test
    fun `getEmployees, when api call fails, it returns failure result`() = runTest {
        // Given
        val expectedException = UnknownHostException("No internet")
        coEvery { apiService.getEmployees() } throws expectedException

        // When
        val result = dataSource.getEmployees()

        // Then
        assertEquals(Result.failure<EmployeeListResponse>(expectedException), result)
    }
}