package com.egarcia.employee.directory.presentation.util

import com.egarcia.employee.directory.data.repository.ResponseBehavior

fun swapBehavior(responseBehavior: ResponseBehavior): ResponseBehavior {
    return when (responseBehavior) {
        ResponseBehavior.EMPLOYEES -> {
            ResponseBehavior.EMPLOYEES_MALFORMED
        }

        ResponseBehavior.EMPLOYEES_MALFORMED -> {
            ResponseBehavior.EMPLOYEES_EMPTY
        }

        ResponseBehavior.EMPLOYEES_EMPTY -> {
            ResponseBehavior.EMPLOYEES
        }
    }
}