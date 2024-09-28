@file:JvmName("RoundingFloat")

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
