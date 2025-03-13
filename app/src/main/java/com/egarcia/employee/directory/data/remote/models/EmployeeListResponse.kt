package com.egarcia.employee.directory.data.remote.models

import com.google.gson.annotations.SerializedName

/**
 * Represents a list of employees.
 *
 * @property employees The list of employees.
 */
data class EmployeeListResponse(
    @SerializedName("employees") var employees: ArrayList<EmployeeResponse> = arrayListOf()
)

fun EmployeeListResponse.isValid(): Boolean {
    for (employee in employees) {
        if (employee.isValid().not()) {
            return false
        }
    }
    return true
}