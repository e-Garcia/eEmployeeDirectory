package com.egarcia.employee.directory.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egarcia.employee.directory.data.repository.EmployeeListRepository
import com.egarcia.employee.directory.data.repository.ResponseBehavior
import com.egarcia.employee.directory.domain.models.Employee
import com.egarcia.employee.directory.domain.utils.toEmployee
import com.egarcia.employee.directory.presentation.util.swapBehavior
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

    private var responseBehavior = ResponseBehavior.EMPLOYEES

    // Backing property to avoid state updates from other classes
    private val _uiState =
        MutableStateFlow<EmployeesListUiState>(EmployeesListUiState.Success(emptyList()))

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<EmployeesListUiState> = _uiState

    fun swapResponse() {
        responseBehavior = swapBehavior(responseBehavior)
        fetchEmployees()
    }

    fun fetchEmployees(responseBehavior: ResponseBehavior = this.responseBehavior) {
        viewModelScope.launch {
            _uiState.value = EmployeesListUiState.Loading
            repository.getEmployees(responseBehavior).onSuccess { data ->
                _uiState.value =
                    EmployeesListUiState.Success(data.employees.map { it.toEmployee() })
            }.onFailure { error ->
                _uiState.value = EmployeesListUiState.Error(error)
            }
        }
    }

    /** Represents different states for the EmployeesList screen **/
    sealed class EmployeesListUiState {
        data class Success(val employees: List<Employee>) : EmployeesListUiState()
        data class Error(val exception: Throwable) : EmployeesListUiState()
        data object Loading : EmployeesListUiState()
    }

}