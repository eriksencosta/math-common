package com.eriksencosta.math.common.caching

import org.junit.jupiter.api.assertThrows
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertEquals

class CacheConfigTest {
    @Test
    fun `Throw exception when maximum items is lower than or equal to zero`() =
        assertThrows<IllegalArgumentException> { CacheConfig().apply { maximumItems = 0 } }.run {
            assertEquals("The maximumItems value must be greater than 0", message)
        }

    @Test
    fun `Throw exception when expiration is lower than or equal to zero`() =
        assertThrows<IllegalArgumentException> { CacheConfig().apply { expirationTime = 0 } }.run {
            assertEquals("The expirationTime value must be greater than 0", message)
        }

    @Test
    fun `Create a CacheConfig`() {
        val config = CacheConfig().apply {
            maximumItems = 100
            expirationTime = 1
            expirationTimeUnit = TimeUnit.HOURS
        }

        assertEquals(100, config.maximumItems)
        assertEquals(1, config.expirationTime)
        assertEquals(TimeUnit.HOURS, config.expirationTimeUnit)
    }
}
