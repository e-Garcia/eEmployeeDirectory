package com.egarcia.employee.directory.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egarcia.employee.directory.data.repository.EmployeeListRepository
import com.egarcia.employee.directory.domain.models.Employee
import com.egarcia.employee.directory.domain.utils.toEmployee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the list of employees.
 *
 * This class is annotated with `@HiltViewModel` to enable dependency injection with Hilt.
 * It interacts with the [EmployeeListRepository] to fetch employee data and exposes
 * the data and any errors through [StateFlow]s.
 *
 * @property repository The repository responsible for fetching employee data.
 * @see EmployeeListRepository for implementation details
 */
@HiltViewModel
class EmployeeListViewModel @Inject constructor(private val repository: EmployeeListRepository) :
    ViewModel() {
    private val _employees = MutableStateFlow(emptyList<Employee>())
    val employees: StateFlow<List<Employee>> = _employees

    private val _employeesError = MutableStateFlow(Throwable())
    val employeesError: StateFlow<Throwable> = _employeesError

    fun getEmployees() {
        viewModelScope.launch {
            repository.getEmployees()
                .onSuccess { data ->
                    _employees.value = data.employees.map { it.toEmployee() }
                }
                .onFailure { error ->
                    _employeesError.value = error
                }
        }
    }
}