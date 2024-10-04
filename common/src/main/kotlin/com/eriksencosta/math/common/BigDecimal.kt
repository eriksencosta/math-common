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

@file:JvmName("BigDecimal")

package com.eriksencosta.math.common

import java.math.BigDecimal

/**
 * Rounds the BigDecimal to a given precision scale using [java.math.RoundingMode.HALF_EVEN] rounding mode.
 *
 * @receiver [BigDecimal]
 * @param[scale] The scale to round a value.
 * @return The rounded value.
 */
public infix fun BigDecimal.round(scale: Int): BigDecimal = Rounding.to(scale).round(this)

/**
 * Rounds the BigDecimal using the given [Rounding].
 *
 * @receiver [BigDecimal]
 * @param[rounding] The [Rounding] strategy to round the value.
 * @return The rounded value.
 */
public infix fun BigDecimal.round(rounding: Rounding): BigDecimal = rounding.round(this)

/**
 * Rounds the BigDecimal to a given precision scale using [java.math.RoundingMode.HALF_EVEN] rounding mode.
 *
 * @receiver (() -> BigDecimal)
 * @param[scale] The scale to round a value.
 * @return The rounded value.
 */
public fun (() -> BigDecimal).round(scale: Int): BigDecimal = Rounding.to(scale).round(this)

/**
 * Rounds the BigDecimal using the given [Rounding].
 *
 * @receiver (() -> BigDecimal)
 * @param[rounding] The [Rounding] strategy to round the value.
 * @return The rounded value.
 */
public fun (() -> BigDecimal).round(rounding: Rounding): BigDecimal = rounding.round(this)
