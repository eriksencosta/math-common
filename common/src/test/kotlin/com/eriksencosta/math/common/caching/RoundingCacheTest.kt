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

package com.eriksencosta.math.common.caching

import com.eriksencosta.math.common.Rounding
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.math.RoundingMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class RoundingCacheTest {
    @BeforeEach
    fun setup() = Rounding.resetCache()

    @Test
    fun `Throw exception when overriding a previously configured cache`() {
        assertThrows<IllegalStateException> {
            configureCache { maximumItems = 5 }
            configureCache { maximumItems = 10 }
        }.run {
            assertEquals("The factory cache can't be replaced once it is configured or initialized", message)
        }
    }

    @Test
    fun `Throw exception when overriding a previously disabled cache`() {
        assertThrows<IllegalStateException> {
            disableCache()
            configureCache { maximumItems = 10 }
        }.run {
            assertEquals("The factory cache can't be replaced once it is configured or initialized", message)
        }
    }

    @Test
    fun `Throw exception when overriding an initialized cache`() {
        assertThrows<IllegalStateException> {
            Rounding.to(2)
            configureCache { maximumItems = 10 }
        }.run {
            assertEquals("The factory cache can't be replaced once it is configured or initialized", message)
        }
    }

    @Test
    fun `Throw exception when disabling an initialized cache`() {
        assertThrows<IllegalStateException> {
            Rounding.to(2)
            disableCache()
        }.run {
            assertEquals("The factory cache can't be replaced once it is configured or initialized", message)
        }
    }

    @Test
    fun `Disable the cache`() {
        disableCache()

        assertNotSame(Rounding.to(2), Rounding.to(2))
    }

    @Test
    fun `Return a cached object`() {
        assertSame(Rounding.to(2), Rounding.to(2))
        assertSame(Rounding.to(4), Rounding.to(4))
        assertSame(Rounding.to(4, RoundingMode.HALF_UP), Rounding.to(4, RoundingMode.HALF_UP))
        assertSame(Rounding.to(6, RoundingMode.HALF_UP), Rounding.to(6, RoundingMode.HALF_UP))
    }

    companion object {
        @JvmStatic
        @AfterAll
        fun tearDown(): Unit = Rounding.resetCache()
    }
}
