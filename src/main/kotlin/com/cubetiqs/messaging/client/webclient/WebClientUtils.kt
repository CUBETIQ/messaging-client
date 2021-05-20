package com.cubetiqs.messaging.client.webclient

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object WebClientUtils {
    private var webClient: OkHttpClient? = null

    private fun getClient(): OkHttpClient {
        if (this.webClient == null) {
            this.webClient = OkHttpClient()
        }

        return this.webClient!!
    }

    @JvmStatic
    fun makeRequest(request: Request): Response {
        val call = getClient().newCall(request)
        var response: Response? = null
        return try {
            response = call.execute()
            response
        } catch (ex: Exception) {
            throw Exception(ex)
        } finally {
            response?.close()
        }
    }
}