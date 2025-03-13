package com.egarcia.employee.directory.domain.utils

import com.egarcia.employee.directory.data.remote.models.EmployeeResponse
import com.egarcia.employee.directory.domain.models.Employee

fun EmployeeResponse.toEmployee() = Employee(
    uuid = this.uuid,
    fullName = this.fullName,
    photoUrlSmall = this.photoUrlSmall,
    photoUrlLarge = this.photoUrlLarge,
    team = this.team
)