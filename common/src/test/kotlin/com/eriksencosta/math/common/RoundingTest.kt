/*
 * This file is part of the Math Common package.
 *
 * Copyright (c) Eriksen Costa <eriksencosta@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eriksencosta.math.common

import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class RoundingTest {
    private val roundingHalfEven = Rounding.to(1)
    private val roundingHalfDown = Rounding.to(1, RoundingMode.HALF_DOWN)
    private val noRounding = Rounding.no()

    @Test
    fun `Do not round a value`() {
        assertEquals(5.55, noRounding.round { 5.55 })
        assertEquals(5.55f, noRounding.round { 5.55f })
        assertEquals(BigDecimal("5.55"), noRounding.round { BigDecimal("5.55") })
    }

    @Test
    fun `Round a value to decimal places`() {
        assertEquals(5.6, roundingHalfEven.round { 5.55 })
        assertEquals(5.6f, roundingHalfEven.round { 5.55f })
        assertEquals(BigDecimal("5.6"), roundingHalfEven.round { BigDecimal("5.55") })

        assertEquals(5.5, roundingHalfDown.round { 5.55 })
        assertEquals(5.5f, roundingHalfDown.round { 5.55f })
        assertEquals(BigDecimal("5.5"), roundingHalfDown.round { BigDecimal("5.55") })

        assertEquals(5.6, 5.55.round(1))
        assertEquals(5.6f, 5.55f.round(1))
        assertEquals(BigDecimal("5.6"), BigDecimal("5.55").round(1))

        assertEquals(5.6, { 5.55 }.round(1))
        assertEquals(5.6f, { 5.55f }.round(1))
        assertEquals(BigDecimal("5.6"), { BigDecimal("5.55") }.round(1))

        assertEquals(5.6, 5.55.round(roundingHalfEven))
        assertEquals(5.6f, 5.55f.round(roundingHalfEven))
        assertEquals(BigDecimal("5.6"), BigDecimal("5.55").round(roundingHalfEven))

        assertEquals(5.6, { 5.55 }.round(roundingHalfEven))
        assertEquals(5.6f, { 5.55f }.round(roundingHalfEven))
        assertEquals(BigDecimal("5.6"), { BigDecimal("5.55") }.round(roundingHalfEven))

        assertEquals(5.5, 5.55.round(roundingHalfDown))
        assertEquals(5.5f, 5.55f.round(roundingHalfDown))
        assertEquals(BigDecimal("5.5"), BigDecimal("5.55").round(roundingHalfDown))

        assertEquals(5.5, { 5.55 }.round(roundingHalfDown))
        assertEquals(5.5f, { 5.55f }.round(roundingHalfDown))
        assertEquals(BigDecimal("5.5"), { BigDecimal("5.55") }.round(roundingHalfDown))
    }

    @Test
    fun `Round a value to a power of ten`() {
        assertEquals(5560.0, 5555.55.round(-1)) // 5.56E+3
        assertEquals(5600.0, 5555.55.round(-2)) // 5.6E+3
        assertEquals(6000.0, 5555.55.round(-3)) // 6E+3
    }

    @Test
    fun `Rescale the Rounding`() {
        assertEquals(Rounding.to(4, RoundingMode.FLOOR), Rounding.to(2, RoundingMode.FLOOR).with(4))
        assertEquals(Rounding.to(2), Rounding.to(4).with(2))
        assertEquals(Rounding.to(2), Rounding.to(2).with(2))
    }

    @Test
    fun `Throw exception when rescaling a NoRounding`() =
        assertThrows<UnsupportedOperationException> { Rounding.no().with(2) }.run {
            val expected = "Can not convert a NoRounding to a PreciseRounding. If you need rounding support, create " +
                "a new object by calling Rounding.to() method"

            assertEquals(expected, message)
        }

    @Test
    fun `Check for equality`() {
        val rounding1 = Rounding.to(2)
        val rounding2 = Rounding.to(2)
        val rounding3 = PreciseRounding(2, RoundingMode.HALF_EVEN)
        val rounding4 = Rounding.to(2, RoundingMode.FLOOR)
        val rounding5 = PreciseRounding(4, RoundingMode.HALF_EVEN)
        val rounding6 = Rounding.to(Int.MAX_VALUE)
        val rounding7 = Rounding.no()
        val rounding8 = Rounding.no()
        val rounding9 = NoRounding

        assertEquals(rounding1, rounding1, "Same instance")
        assertEquals(rounding1, rounding2, "Same values")
        assertEquals(rounding1, rounding3, "Same values")
        assertNotEquals(rounding1, rounding4, "Different values: mode")
        assertNotEquals(rounding1, rounding5, "Different values: precision")
        assertNotEquals<Rounding>(rounding6, rounding7, "Different Rounding subclass")

        // NoRounding is a singleton.
        assertSame(rounding7, rounding8, "Same instance")
        assertSame(rounding7, rounding9, "Same instance")

        // The library caches the PreciseRounding objects.
        assertSame(rounding1, rounding2)
        assertNotSame(rounding1, rounding3)

        // Coverage cases.
        @Suppress("EqualsNullCall")
        assertFalse(rounding1.equals(null), "Different types: null")
        assertFalse(rounding1.equals(""), "Different types: string")
    }

    @Test
    fun `Order a collection of Rounding`() {
        val unordered = listOf(
            Rounding.to(3),
            Rounding.to(2),
            Rounding.no(),
            Rounding.to(1),
        )

        val ordered = listOf(
            Rounding.to(1),
            Rounding.to(2),
            Rounding.to(3),
            Rounding.no(),
        )

        // I think this is the most illustrative way of testing compareTo() as the sorted() method depends on it.
        // Indeed, it is a transgression to testing practices as it isn't directly calling the behavior under testing.
        assertEquals(ordered, unordered.sorted())
    }

    @Test
    fun `Calculate the hashCode`() {
        assertEquals(998, roundingHalfEven.hashCode())
        assertEquals(997, roundingHalfDown.hashCode())
    }

    @Test
    fun `Convert to string`() {
        assertEquals("NoRounding", Rounding.no().toString())
        assertEquals("PreciseRounding[2 HALF_EVEN]", Rounding.to(2).toString())
    }
}
