package com.egarcia.employee.directory.domain.models

//TODO: Adapt fields to api required fields and add validators
data class Employee(
    var uuid: String? = null,
    var fullName: String? = null,
    var photoUrlSmall: String? = null,
    var photoUrlLarge: String? = null,
    var team: String? = null,
)