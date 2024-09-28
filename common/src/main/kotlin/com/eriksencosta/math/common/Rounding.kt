package com.eriksencosta.math.common

import java.math.BigDecimal
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
    public open val mode: RoundingMode = defaultRoundingMode

    /**
     * Returns a new [Rounding] with the given precision, keeping the current rounding [mode].
     *
     * @param[precision] The precision scale to round a value.
     * @return A [Rounding] object.
     */
    public open infix fun with(precision: Int): Rounding = if (this.precision == precision)
        this else to(precision, mode)

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
     * Rounds the value returned by the function [block].
     *
     * @param[block] A function block.
     * @return A rounded value.
     */
    @OptIn(ExperimentalTypeInference::class)
    @OverloadResolutionByLambdaReturnType
    public fun round(block: () -> BigDecimal): BigDecimal = round(block())

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

    /**
     * Rounds the given value.
     *
     * @param[value] A value.
     * @return A rounded value.
     */
    public abstract fun round(value: BigDecimal): BigDecimal

    override fun compareTo(other: Rounding): Int = precision.compareTo(other.precision)

    override fun equals(other: Any?): Boolean = (other as Rounding).let {
        precision == it.precision &&
            mode == it.mode
    }

    override fun hashCode(): Int = hash(precision, mode.ordinal)

    /**
     * A [Rounding] factory.
     */
    public companion object Factory {
        private val defaultRoundingMode: RoundingMode = RoundingMode.HALF_EVEN

        /**
         * Creates a [NoRounding] instance.
         *
         * @return A [NoRounding] object.
         */
        public fun no(): NoRounding = NoRounding

        /**
         * Creates a [PreciseRounding] instance.
         *
         * @param[precision] The precision scale to round a value.
         * @param[mode] The rounding mode policy to round the number.
         * @return A [PreciseRounding] object.
         */
        public fun to(precision: Int, mode: RoundingMode = defaultRoundingMode): PreciseRounding =
            PreciseRounding(precision, mode)
    }
}

/**
 * Strategy that does not round a value.
 */
public object NoRounding : Rounding() {
    override fun with(precision: Int): Rounding = throw UnsupportedOperationException(
        "Can not convert a NoRounding to a PreciseRounding. If you need rounding support, create a new object by " +
            "calling Rounding.to() method"
    )
    override fun round(value: Double): Double = value
    override fun round(value: Float): Float = value
    override fun round(value: BigDecimal): BigDecimal = value
    override fun toString(): String = "NoRounding"
}

/**
 * Strategy to round a value precisely.
 */
public class PreciseRounding internal constructor(
    override val precision: Int,
    override val mode: RoundingMode
) : Rounding() {
    override fun round(value: Double): Double = round(value.toBigDecimal()).toDouble()
    override fun round(value: Float): Float = round(value.toBigDecimal()).toFloat()
    override fun round(value: BigDecimal): BigDecimal = value.setScale(precision, mode)
    override fun equals(other: Any?): Boolean = other is PreciseRounding && super.equals(other)
    override fun hashCode(): Int = super.hashCode()
    override fun toString(): String = "PreciseRounding[%d %s]".format(precision, mode)
}
