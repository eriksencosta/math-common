package com.eriksencosta.math.common.caching

import java.util.concurrent.TimeUnit

/**
 * Cache configuration options used by the [configureCache] function.
 *
 * @see[configureCache]
 */
public class CacheConfig internal constructor() {
    /**
     * The number of objects to keep in the cache. Defaults to 50.
     */
    public var maximumItems: Long = DEFAULT_MAXIMUM_ITEMS
        set(value) = require(0 < value) {
            "The maximumItems value must be greater than 0"
        }.let { field = value }

    /**
     * The amount of time to keep an object in the cache. Defaults to 60 (minutes).
     */
    public var expirationTime: Long = DEFAULT_EXPIRATION_TIME
        set(value) = require(0 < value) {
            "The expirationTime value must be greater than 0"
        }.let { field = value }

    /**
     * The time unit of [expirationTime]. Defaults to [TimeUnit.MINUTES].
     */
    public var expirationTimeUnit: TimeUnit = TimeUnit.MINUTES

    private companion object {
        private const val DEFAULT_MAXIMUM_ITEMS: Long = 50
        private const val DEFAULT_EXPIRATION_TIME: Long = 60
    }
}
