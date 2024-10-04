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

@file:JvmName("Float")

package com.eriksencosta.math.common

/**
 * Rounds the float value to a given precision scale using [java.math.RoundingMode.HALF_EVEN] rounding mode.
 *
 * @receiver [Float]
 * @param[scale] The scale to round a value.
 * @return The rounded value.
 */
public infix fun Float.round(scale: Int): Float = Rounding.to(scale).round(this)

/**
 * Rounds the float value using the given [Rounding].
 *
 * @receiver [Float]
 * @param[rounding] The [Rounding] strategy to round the value.
 * @return The rounded value.
 */
public infix fun Float.round(rounding: Rounding): Float = rounding.round(this)

/**
 * Rounds the double value to a given precision scale using [java.math.RoundingMode.HALF_EVEN] rounding mode.
 *
 * @receiver (() -> Float)
 * @param[scale] The scale to round a value.
 * @return The rounded value.
 */
public fun (() -> Float).round(scale: Int): Float = Rounding.to(scale).round(this)

/**
 * Rounds the double value using the given [Rounding].
 *
 * @receiver (() -> Float)
 * @param[rounding] The [Rounding] strategy to round the value.
 * @return The rounded value.
 */
public fun (() -> Float).round(rounding: Rounding): Float = rounding.round(this)
