/*
 * This file is part of the Math Common package.
 *
 * Copyright (c) Eriksen Costa <eriksencosta@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
