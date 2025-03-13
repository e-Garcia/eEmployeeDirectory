package com.egarcia.employee.directory.presentation.viewmodels

import com.egarcia.employee.directory.data.remote.models.EmployeeListResponse
import com.egarcia.employee.directory.data.remote.models.EmployeeResponse
import com.egarcia.employee.directory.data.repository.EmployeeListRepository
import com.egarcia.employee.directory.domain.models.Employee
import com.egarcia.employee.directory.rules.MainDispatcherRule
import io.mockk.Awaits
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.UnknownHostException

class EmployeeListViewModelTest {

    private lateinit var viewModel: EmployeeListViewModel
    private lateinit var repository: EmployeeListRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        repository = mockk()
        viewModel = EmployeeListViewModel(repository)
    }

    @After
    fun tearDown() {
        // Do nothing
    }

    @Test
    fun `fetch employees when success returns success state`() = runTest{
        // Given
        val employeesResponse = EmployeeListResponse(createEmployeeResponseList())
        val expectedEmployees = createEmployeeList()
        coEvery { repository.getEmployees() } returns Result.success(employeesResponse)

        // When
        viewModel.fetchEmployees()

        // Then
        val actualState = viewModel.uiState.first()
        assertEquals(
            EmployeeListViewModel.EmployeesListUiState.Success(expectedEmployees),
            actualState
        )
    }

    @Test
    fun `fetch employees when error returns error state`() = runTest {
        // Given
        val error = UnknownHostException("No internet")
        coEvery { repository.getEmployees() } returns Result.failure(error)

        // When
        viewModel.fetchEmployees()

        // Then
        val actualState = viewModel.uiState.first()
        assertEquals(EmployeeListViewModel.EmployeesListUiState.Error(error), actualState)
    }

    @Test
    fun `fetch employees when loading returns loading state`() = runTest {
        // Given
        coEvery { repository.getEmployees() } just Awaits

        // When
        viewModel.fetchEmployees()

        // Then
        val actualState = viewModel.uiState.first()
        assertEquals(EmployeeListViewModel.EmployeesListUiState.Loading, actualState)
    }

    // Helper method to create test data
    private fun createEmployeeResponseList(): ArrayList<EmployeeResponse> {
        return arrayListOf(
            EmployeeResponse(
                uuid = "1",
                fullName = "John Doe",
                phoneNumber = "123-456-7890",
                emailAddress = "john.doe@example.com",
                biography = "Bio 1",
                photoUrlSmall = "url1",
                photoUrlLarge = "url2",
                team = "Team 1",
                employeeType = "FULL_TIME"
            ),
            EmployeeResponse(
                uuid = "2",
                fullName = "Jane Smith",
                phoneNumber = "987-654-3210",
                emailAddress = "jane.smith@example.com",
                biography = "Bio 2",
                photoUrlSmall = "url3",
                photoUrlLarge = "url4",
                team = "Team 2",
                employeeType = "CONTRACTOR"
            )
        )
    }

    private fun createEmployeeList(): List<Employee> {
        return listOf(
            Employee(
                uuid = "1",
                fullName = "John Doe",
                photoUrlSmall = "url1",
                photoUrlLarge = "url2",
                team = "Team 1"),
            Employee(
                uuid = "2",
                fullName = "Jane Smith",
                photoUrlSmall = "url3",
                photoUrlLarge = "url4",
                team = "Team 2")
        )
    }
}