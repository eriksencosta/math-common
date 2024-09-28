# Module Math Common

Math conventions to reduce boilerplate code. Useful for rounding values:

    import java.math.RoundingMode

    val rounding = Rounding.to(2, RoundingMode.FLOOR)

    rounding.round(1.257) // 1.25
    rounding.round(1.253) // 1.25

    // Alternatively:

    1.257.round(rounding) // 1.25
    1.253.round(rounding) // 1.25

You can also just pass the number of desired decimal places to round, in which case [HALF_EVEN][java.math.RoundingMode]
is used as the rounding mode:

    1.257.round(2) // 1.26

To round the result of a calculation, pass a function block to the [round][com.eriksencosta.math.common.Rounding]
method:

    Rounding.to(2).round { 3.14159 * 10.0.squared() } // 314.16

    // Alternatively:

    { 3.14159 * 10.0.squared() }.round(2) // 314.16


## Installation and documentation

The [installation procedures](https://github.com/eriksencosta/math-common#installation) and the
[how-to-use](https://github.com/eriksencosta/math-common#usage) documentation can be read in the
[project repository on GitHub](https://github.com/eriksencosta/math-common).


# Package com.eriksencosta.math.common

Core functions and types.
