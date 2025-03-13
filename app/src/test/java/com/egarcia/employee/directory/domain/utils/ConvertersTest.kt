package com.egarcia.employee.directory.domain.utils

import com.egarcia.employee.directory.data.remote.models.EmployeeResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class EmployeeResponseToEmployeeTest {

    @Test
    fun `toEmployee, when valid employee, response returns employee`() = runTest {
        // Given
        val employeeResponse = EmployeeResponse(
            uuid = "123",
            fullName = "John Doe",
            phoneNumber = "555-123-4567",
            emailAddress = "john.doe@example.com",
            biography = "A short bio",
            photoUrlSmall = "https://example.com/small.jpg",
            photoUrlLarge = "https://example.com/large.jpg",
            team = "Engineering",
            employeeType = "FULL_TIME"
        )

        // When
        val employee = employeeResponse.toEmployee()

        // Then
        assertEquals(employeeResponse.uuid, employee.uuid)
        assertEquals(employeeResponse.fullName, employee.fullName)
        assertEquals(employeeResponse.photoUrlSmall, employee.photoUrlSmall)
        assertEquals(employeeResponse.photoUrlLarge, employee.photoUrlLarge)
        assertEquals(employeeResponse.team, employee.team)
    }

    @Test
    fun `toEmployee, when empty fields, returns employee with empty fields`() = runTest {
        // Given
        val employeeResponse = EmployeeResponse(
            uuid = "",
            fullName = "",
            phoneNumber = "",
            emailAddress = "",
            biography = "",
            photoUrlSmall = "",
            photoUrlLarge = "",
            team = "",
            employeeType = ""
        )

        // When
        val employee = employeeResponse.toEmployee()

        // Then
        assertEquals("", employee.uuid)
        assertEquals("", employee.fullName)
        assertEquals("", employee.photoUrlSmall)
        assertEquals("", employee.photoUrlLarge)
        assertEquals("", employee.team)
    }

    @Test
    fun `toEmployee, when null fields, returns employee with null fields`() = runTest {
        // Given
        val employeeResponse = EmployeeResponse(
            uuid = null,
            fullName = null,
            phoneNumber = null,
            emailAddress = null,
            biography = null,
            photoUrlSmall = null,
            photoUrlLarge = null,
            team = null,
            employeeType = null
        )

        // When
        val employee = employeeResponse.toEmployee()

        // Then
        assertEquals(null, employee.uuid)
        assertEquals(null, employee.fullName)
        assertEquals(null, employee.photoUrlSmall)
        assertEquals(null, employee.photoUrlLarge)
        assertEquals(null, employee.team)
    }
}