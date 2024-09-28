@file:JvmName("Double")

package com.eriksencosta.math.common

/**
 * Rounds the double value to a given precision scale using [java.math.RoundingMode.HALF_EVEN] rounding mode.
 *
 * @receiver [Double]
 * @param[scale] The scale to round a value.
 * @return The rounded value.
 */
public infix fun Double.round(scale: Int): Double = Rounding.to(scale).round(this)

/**
 * Rounds the double value using the given [Rounding].
 *
 * @receiver [Double]
 * @param[rounding] The [Rounding] strategy to round the value.
 * @return The rounded value.
 */
public infix fun Double.round(rounding: Rounding): Double = rounding.round(this)

/**
 * Rounds the double value to a given precision scale using [java.math.RoundingMode.HALF_EVEN] rounding mode.
 *
 * @receiver (() -> Double)
 * @param[scale] The scale to round a value.
 * @return The rounded value.
 */
public fun (() -> Double).round(scale: Int): Double = Rounding.to(scale).round(this)

/**
 * Rounds the double value using the given [Rounding].
 *
 * @receiver (() -> Double)
 * @param[rounding] The [Rounding] strategy to round the value.
 * @return The rounded value.
 */
public fun (() -> Double).round(rounding: Rounding): Double = rounding.round(this)
