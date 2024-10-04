# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to
[Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Breaking changes are highlighted with the [BC] prefix.

## Notes on the 0.x version

The 0.x version may have breaking changes (as foreseen in the Semantic Versioning Specification). Nevertheless, efforts
will be taken to minimize this kind of change.

## 0.2.0 (2024-10-04)

### Added

* Rounding support for `BigDecimal`
* Exponentiation functions for `BigDecimal`
* Internal caching for `PreciseRounding` objects

### Changed

* [BC] Default rounding to `HALF_EVEN`
  * The project started with `HALF_UP` because it was inspired by previous working experience which used it. However,
    `HALF_EVEN` is the most recommended rounding mode, being used as the default mode for floating-point arithmetic in
    IEEE 754. `HALF_EVEN` is also known as banker's rounding
* [BC] `Rounding` companion object name to `Factory`
  *  This may lead to issues in case two dependencies use Math Common with different versions (0.2.0 and 0.1.0) or if
     using the companion object qualified name, i.e., calling `Rounding.to()` as `Rounding.Companion.to()`
* [BC] `Rounding.with(precision: Int)` to throw an exception when the object is `NoRounding`
  * If you are using the `NoRounding` strategy and needs to round a value, create a `PreciseRounding` through
    `Rounding.to()`


## 0.1.0 (2024-03-28)

Initial release.
