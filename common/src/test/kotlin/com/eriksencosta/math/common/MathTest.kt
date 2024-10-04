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

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class MathTest {
    @Test
    fun `Number squared`() {
        assertEquals(4.0, 2.0.squared())
        assertEquals(4.0f, 2.0f.squared())
        assertEquals(4L, 2L.squared())
        assertEquals(4, 2.squared())
        assertEquals(BigDecimal("4"), BigDecimal("2").squared())
    }

    @Test
    fun `Number cubed`() {
        assertEquals(8.0, 2.0.cubed())
        assertEquals(8.0f, 2.0f.cubed())
        assertEquals(8L, 2L.cubed())
        assertEquals(8, 2.cubed())
        assertEquals(BigDecimal("8"), BigDecimal("2").cubed())
    }
}
