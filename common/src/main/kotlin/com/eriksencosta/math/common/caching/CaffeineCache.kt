package com.eriksencosta.math.common.caching

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Scheduler
import com.github.benmanes.caffeine.cache.Cache as CaffeineCache

internal class CaffeineCache<T>(config: CacheConfig) : Cache<T> {
    private val cache: CaffeineCache<String, T> = Caffeine.newBuilder()
        .scheduler(Scheduler.systemScheduler())
        .maximumSize(config.maximumItems)
        .expireAfterAccess(config.expirationTime, config.expirationTimeUnit)
        .build()

    override fun isInitialized(): Boolean = 0 < cache.estimatedSize()

    @Suppress("UNCHECKED_CAST")
    override fun get(key: String, block: () -> T): T = cache.get(key) { block() } as T

    override fun clean() = cache.invalidateAll()
}

internal fun <T> createCache(config: CacheConfig.() -> Unit = {}): Cache<T> = CaffeineCache(CacheConfig().apply(config))
