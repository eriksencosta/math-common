package com.eriksencosta.math.common

import java.math.RoundingMode
import java.util.Objects.hash
import kotlin.experimental.ExperimentalTypeInference

/**
 * Strategy to round a [Double] or [Float] value to a given precision scale using a specified [java.math.RoundingMode]
 * policy. The companion object exposes two factory methods to create the appropriate strategy:
 *
 * * [to]: creates a [PreciseRounding] object, which rounds a value to a specific precision scale and by a specific
 *   rounding mode
 * * [no]: creates a [NoRounding] object, which doesn't round a value
 *
 * If you need to round a value, call the [to] factory method:
 *
 * ```
 * val rounding = Rounding.to(1, RoundingMode.UP)
 * rounding.round(5.76) // 5.8
 * ```
 *
 * To round up to the nearest power of ten, use a negative [precision]:
 *
 * ```
 * val rounding = Rounding.to(-1)
 * rounding.round(5555.55) // 5560.0 -- or, 5.56E3
 * ```
 *
 * See [significant figures](https://en.wikipedia.org/wiki/Significant_figures) on Wikipedia for an arithmetic
 * background.
 */
public sealed class Rounding : Comparable<Rounding> {
    /**
     * The precision to round the value (i.e., number of significant figures to use).
     */
    public open val precision: Int = Int.MAX_VALUE

    /**
     * The rounding mode used to round the decimal value.
     */
    public open val mode: RoundingMode = RoundingMode.HALF_UP

    public companion object {
        /**
         * Creates a `NoRounding` instance.
         *
         * @return A [NoRounding] object.
         */
        public fun no(): NoRounding = NoRounding()

        /**
         * Creates a `PreciseRounding` instance.
         *
         * @param[precision] The precision scale to round a value.
         * @param[mode] The rounding mode policy to round the number.
         * @return A [PreciseRounding] object.
         */
        public fun to(precision: Int, mode: RoundingMode = RoundingMode.HALF_UP): PreciseRounding =
            PreciseRounding(precision, mode)
    }

    /**
     * Returns a new `Rounding` with the given precision, keeping the current rounding [mode].
     *
     * @param[precision] The precision scale to round a value.
     * @return A [Rounding] object.
     */
    public infix fun with(precision: Int): Rounding =
        if (this.precision == precision && this.mode == RoundingMode.HALF_UP) this else to(precision, mode)

    /**
     * Rounds the value returned by the function [block].
     *
     * @param[block] A function block.
     * @return A rounded value.
     */
    public fun round(block: () -> Double): Double = round(block())

    /**
     * Rounds the value returned by the function [block].
     *
     * @param[block] A function block.
     * @return A rounded value.
     */
    @OptIn(ExperimentalTypeInference::class)
    @OverloadResolutionByLambdaReturnType
    public fun round(block: () -> Float): Float = round(block())

    /**
     * Rounds the given value.
     *
     * @param[value] A value.
     * @return A rounded value.
     */
    public abstract fun round(value: Double): Double

    /**
     * Rounds the given value.
     *
     * @param[value] A value.
     * @return A rounded value.
     */
    public abstract fun round(value: Float): Float

    override fun compareTo(other: Rounding): Int = precision.compareTo(other.precision)

    override fun equals(other: Any?): Boolean = this === other ||
        other is Rounding && precision == other.precision && mode == other.mode

    override fun hashCode(): Int = hash(precision, mode.ordinal)
}

/**
 * Strategy that does not round a value.
 */
public class NoRounding internal constructor() : Rounding() {
    override fun round(value: Double): Double = value
    override fun round(value: Float): Float = value
    override fun toString(): String = "NoRounding"
}

/**
 * Strategy to round a value precisely.
 */
public class PreciseRounding internal constructor(override val precision: Int, override val mode: RoundingMode) :
    Rounding() {
    override fun round(value: Double): Double = value.toBigDecimal().setScale(precision, mode).toDouble()
    override fun round(value: Float): Float = value.toBigDecimal().setScale(precision, mode).toFloat()
    override fun toString(): String = "PreciseRounding[%d %s]".format(precision, mode)
}
