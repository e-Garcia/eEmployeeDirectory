package com.egarcia.employee.directory.data.remote.models

import com.google.gson.annotations.SerializedName

/**
 * Represents an employee.
 *
 * @property uuid The unique identifier for the employee. Represented as a UUID.
 * @property fullName The full name of the employee.
 * @property phoneNumber The phone number of the employee, sent as an unformatted string (eg, 5556661234). (optional).
 * @property emailAddress The email address of the employee.
 * @property biography A short, tweet-length (~300 chars) string that the employee provided to describe themselves. (optional).
 * @property photoUrlSmall The URL of the employee’s small photo. Useful for list view. (optional).
 * @property photoUrlLarge The URL of the employee’s full-size photo. (optional).
 * @property team The team they are on, represented as a human readable string.
 * @property employeeType How the employee is classified. (FULL_TIME, PART_TIME, CONTRACTOR)
 */
data class EmployeeResponse(
    @SerializedName("uuid") var uuid: String? = null, // Required
    @SerializedName("full_name") var fullName: String? = null, // Required
    @SerializedName("phone_number") var phoneNumber: String? = null, // Not required
    @SerializedName("email_address") var emailAddress: String? = null, // Required
    @SerializedName("biography") var biography: String? = null, // Not required
    @SerializedName("photo_url_small") var photoUrlSmall: String? = null, // Not required
    @SerializedName("photo_url_large") var photoUrlLarge: String? = null, // Not required
    @SerializedName("team") var team: String? = null, // Required
    @SerializedName("employee_type") var employeeType: String? = null // Required
)

fun EmployeeResponse.isValid(): Boolean {
    return uuid.isNullOrEmpty().not() &&
            fullName.isNullOrEmpty().not() &&
            emailAddress.isNullOrEmpty().not() &&
            team.isNullOrEmpty().not() &&
            employeeType.isNullOrEmpty().not()
}