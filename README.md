# Math Common

![Codacy grade](https://img.shields.io/codacy/grade/78b28d3fdf8f4a869b2222b623eb0ed0)
![Codacy coverage](https://img.shields.io/codacy/coverage/78b28d3fdf8f4a869b2222b623eb0ed0)

Math conventions to reduce boilerplate code.

## Installation

Add Math Common to your Gradle build script:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.eriksencosta.math:common:0.2.0")
}
```

If you're using Maven, add to your POM xml file:

```xml
<dependency>
    <groupId>com.eriksencosta.math</groupId>
    <artifactId>common</artifactId>
    <version>0.2.0</version>
</dependency>
```

Math Common is not compatible with the Android SDK at the moment.

## Usage

### Rounding

The library provides the `Rounding` [strategy](https://refactoring.guru/design-patterns/strategy) to make rounding
easier:

```kotlin
1.25.round(1) // 1.3
```

By default, the [RoundingMode.HALF_EVEN](https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html#HALF_EVEN) is
used for rounding (it is the rounding logic commonly taught at school). If you want to use a different mode, create a
`Rounding` and use it:

```kotlin
import java.math.RoundingMode

val rounding = Rounding.to(2, RoundingMode.FLOOR)

rounding.round(1.257) // 1.25
rounding.round(1.253) // 1.25

// Alternatively:

1.257.round(rounding) // 1.25
1.253.round(rounding) // 1.25
```

If you want to round the result of a calculation, use the overloaded `round()` method which accepts a function as
parameter:

```kotlin
Rounding.to(2).round { 3.14159 * 10.0.squared() } // 314.16
```

Alternatively:

```kotlin
{ 3.14159 * 10.0.squared() }.round(2) // 314.16
```

### Exponentiation

The extension functions `squared` and `cubed` are available for `BigDecimal`, `Double`, `Float`, `Long`, and `Int`:

```kotlin
2.squared() // 4
2.0.cubed() // 8.0
```

## Performance

The library caches the `Rounding` objects by default. You can configure the cache by calling `configureCache()`:

```kotlin
configureCache {
    maximumItems = 100
    expirationTime = 2
    expirationTimeUnit = TimeUnit.HOURS
}

Rounding.to(2) === Rounding.to(2) // true
```

The previous example will configure the cache to store up to 100 `Rounding` objects, and a cached object will live for up to two hours. If you want to disable the cache, call `disableCache()`:

```kotlin
disableCache()

Rounding.to(2) === Rounding.to(2) // false
```

## API documentation

Read the [API documentation](https://blog.eriksen.com.br/opensource/math-common/) for further details.

## License

[The Apache Software License, Version 2.0](https://choosealicense.com/licenses/apache/)
