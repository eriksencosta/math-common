@file:JvmName("RoundingBigDecimal")

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
