package com.egarcia.employee.directory.data.remote.models

import com.google.gson.annotations.SerializedName

data class EmployeeListResponse(
    @SerializedName("employees") var employees: ArrayList<EmployeeResponse> = arrayListOf()
)

data class EmployeeResponse(
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("full_name") var fullName: String? = null,
    @SerializedName("phone_number") var phoneNumber: String? = null,
    @SerializedName("email_address") var emailAddress: String? = null,
    @SerializedName("biography") var biography: String? = null,
    @SerializedName("photo_url_small") var photoUrlSmall: String? = null,
    @SerializedName("photo_url_large") var photoUrlLarge: String? = null,
    @SerializedName("team") var team: String? = null,
    @SerializedName("employee_type") var employeeType: String? = null
)