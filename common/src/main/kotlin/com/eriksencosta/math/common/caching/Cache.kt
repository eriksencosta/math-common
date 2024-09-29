package com.eriksencosta.math.common.caching

internal interface Cache<T> {
    fun isInitialized(): Boolean
    fun get(key: String, block: () -> T): T
    fun clean()
}

internal class NoCache<T> : Cache<T> {
    override fun isInitialized(): Boolean = true
    override fun get(key: String, block: () -> T): T = block()
    override fun clean() = Unit
}

internal class DefaultCache<T>(private val cache: Cache<T>) : Cache<T> {
    override fun isInitialized() = cache.isInitialized()
    override fun get(key: String, block: () -> T): T = cache.get(key, block)
    override fun clean() = cache.clean()
}
