package com.egarcia.employee.directory.data.remote.models

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmployeeResponseTest {

    @Test
    fun `isValid returns true when all required fields are present`() {
        val employee = createValidEmployeeResponse()
        assertTrue(employee.isValid())
    }

    @Test
    fun `isValid returns false when uuid is null`() {
        val employee = createValidEmployeeResponse().copy(uuid = null)
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when fullName is null`() {
        val employee = createValidEmployeeResponse().copy(fullName = null)
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when emailAddress is null`() {
        val employee = createValidEmployeeResponse().copy(emailAddress = null)
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when team is null`() {
        val employee = createValidEmployeeResponse().copy(team = null)
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when employeeType is null`() {
        val employee = createValidEmployeeResponse().copy(employeeType = null)
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when uuid is empty`() {
        val employee = createValidEmployeeResponse().copy(uuid = "")
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when fullName is empty`() {
        val employee = createValidEmployeeResponse().copy(fullName = "")
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when emailAddress is empty`() {
        val employee = createValidEmployeeResponse().copy(emailAddress = "")
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when team is empty`() {
        val employee = createValidEmployeeResponse().copy(team = "")
        assertFalse(employee.isValid())
    }

    @Test
    fun `isValid returns false when employeeType is empty`() {
        val employee = createValidEmployeeResponse().copy(employeeType = "")
        assertFalse(employee.isValid())
    }
}

fun createValidEmployeeResponse(): EmployeeResponse {
    return EmployeeResponse(
        uuid = "1",
        fullName = "John Doe",
        emailAddress = "john.doe@example.com",
        team = "Team A",
        employeeType = "FULL_TIME"
    )
}