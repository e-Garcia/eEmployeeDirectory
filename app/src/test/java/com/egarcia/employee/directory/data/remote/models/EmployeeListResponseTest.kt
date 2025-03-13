package com.egarcia.employee.directory.data.remote.models

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmployeeListResponseTest {

    @Test
    fun `isValid returns true when all employees are valid`() {
        val employee1 = createValidEmployeeResponse()
        val employee2 = EmployeeResponse(
            uuid = "2",
            fullName = "Jane Smith",
            emailAddress = "jane.smith@example.com",
            team = "Team B",
            employeeType = "PART_TIME"
        )
        val employeeListResponse = EmployeeListResponse(arrayListOf(employee1, employee2))

        assertTrue(employeeListResponse.isValid())
    }

    @Test
    fun `isValid returns false when one employee is invalid`() {
        val validEmployee = createValidEmployeeResponse()
        val invalidEmployee = EmployeeResponse(
            uuid = null,
            fullName = "Invalid Employee",
            emailAddress = "invalid@example.com",
            team = "Team C",
            employeeType = "CONTRACTOR"
        )
        val employeeListResponse = EmployeeListResponse(arrayListOf(validEmployee, invalidEmployee))

        assertFalse(employeeListResponse.isValid())
    }

    @Test
    fun `isValid returns true when employee list is empty`() {
        val employeeListResponse = EmployeeListResponse(arrayListOf())
        assertTrue(employeeListResponse.isValid())
    }
}