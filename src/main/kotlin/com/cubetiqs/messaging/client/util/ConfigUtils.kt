package com.cubetiqs.messaging.client.util

object ConfigUtils {
    @JvmStatic
    fun getEnv(key: String, defaultValue: String? = null): String? {
        return System.getenv(key) ?: defaultValue
    }

    @JvmStatic
    fun getProperty(key: String, defaultValue: String? = null): String? {
        return System.getProperty(key, defaultValue)
    }
}