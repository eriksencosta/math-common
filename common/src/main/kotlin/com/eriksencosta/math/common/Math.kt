@file:JvmName("Math")
@file:Suppress("MagicNumber")

package com.eriksencosta.math.common

import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong

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
