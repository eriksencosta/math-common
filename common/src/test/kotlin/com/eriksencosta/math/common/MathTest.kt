package com.eriksencosta.math.common

import kotlin.test.Test
import kotlin.test.assertEquals

class MathTest {
    @Test
    fun `Number squared`() {
        assertEquals(4.0, 2.0.squared())
        assertEquals(4.0f, 2.0f.squared())
        assertEquals(4L, 2L.squared())
        assertEquals(4, 2.squared())
    }

    @Test
    fun `Number cubed`() {
        assertEquals(8.0, 2.0.cubed())
        assertEquals(8.0f, 2.0f.cubed())
        assertEquals(8L, 2L.cubed())
        assertEquals(8, 2.cubed())
    }
}
