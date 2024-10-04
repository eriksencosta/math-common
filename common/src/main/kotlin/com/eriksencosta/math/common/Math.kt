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

@file:JvmName("Math")
@file:Suppress("MagicNumber")

package com.eriksencosta.math.common

import java.math.BigDecimal
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong

/**
 * Returns the square of the number.
 */
public fun BigDecimal.squared(): BigDecimal = this.pow(2)

/**
 * Returns the square of the number.
 */
public fun Double.squared(): Double = this.pow(2)

/**
 * Returns the square of the number.
 */
public fun Float.squared(): Float = this.pow(2)

/**
 * Returns the square of the number.
 */
public fun Long.squared(): Long = this.toFloat().pow(2).roundToLong()

/**
 * Returns the square of the number.
 */
public fun Int.squared(): Int = this.toFloat().pow(2).roundToInt()

/**
 * Returns the cube of the number.
 */
public fun BigDecimal.cubed(): BigDecimal = this.pow(3)

/**
 * Returns the cube of the number.
 */
public fun Double.cubed(): Double = this.pow(3)

/**
 * Returns the cube of the number.
 */
public fun Float.cubed(): Float = this.pow(3)

/**
 * Returns the cube of the number.
 */
public fun Long.cubed(): Long = this.toFloat().pow(3).roundToLong()

/**
 * Returns the cube of the number.
 */
public fun Int.cubed(): Int = this.toFloat().pow(3).roundToInt()
