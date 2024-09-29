package com.eriksencosta.math.common.caching

import com.eriksencosta.math.common.Rounding
import com.eriksencosta.math.common.Rounding.Factory
import com.eriksencosta.math.common.Rounding.Factory.to

/**
 * Configures the [Factory] cache. The function must be called before the cache is initialized (i.e., before any call to
 * the [to] method) and should be called once. Example:
 *
 * ```
 * configureCache {
 *     maximumItems = 100
 *     expirationTime = 2
 *     expirationTimeUnit = TimeUnit.HOURS
 * }
 *
 * val rounding = Rounding.to(2)
 * ```
 *
 * @param[config] A configuration block to set up the cache.
 * @throws[IllegalStateException] When the cache was previously initialized or configured.
 */
public fun configureCache(config: CacheConfig.() -> Unit): Unit = Rounding.configureCache(config)

/**
 * Disables the [Factory] cache. The function must be called before the cache is initialized (i.e., before any call to
 * the [to] method) and should be called once.
 *
 * @throws[IllegalStateException] When the cache was previously initialized or configured.
 */
public fun disableCache(): Unit = Rounding.disableCache()
