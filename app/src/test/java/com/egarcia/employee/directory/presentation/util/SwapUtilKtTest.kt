package com.egarcia.employee.directory.presentation.util

import com.egarcia.employee.directory.data.repository.ResponseBehavior
import org.junit.Assert.assertEquals
import org.junit.Test

class SwapBehaviorTest {

    @Test
    fun `swapBehavior should return EMPLOYEES_MALFORMED when input is EMPLOYEES`() {
        val result = swapBehavior(ResponseBehavior.EMPLOYEES)
        assertEquals(ResponseBehavior.EMPLOYEES_MALFORMED, result)
    }

    @Test
    fun `swapBehavior should return EMPLOYEES_EMPTY when input is EMPLOYEES_MALFORMED`() {
        val result = swapBehavior(ResponseBehavior.EMPLOYEES_MALFORMED)
        assertEquals(ResponseBehavior.EMPLOYEES_EMPTY, result)
    }

    @Test
    fun `swapBehavior should return EMPLOYEES when input is EMPLOYEES_EMPTY`() {
        val result = swapBehavior(ResponseBehavior.EMPLOYEES_EMPTY)
        assertEquals(ResponseBehavior.EMPLOYEES, result)
    }
}