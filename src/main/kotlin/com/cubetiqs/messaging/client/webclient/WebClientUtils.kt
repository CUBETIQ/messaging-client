package com.cubetiqs.messaging.client.webclient

import com.cubetiqs.messaging.client.util.Loggable
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object WebClientUtils : Loggable {
    private var webClient: OkHttpClient? = null

    private fun getClient(): OkHttpClient {
        if (this.webClient == null) {
            this.webClient = OkHttpClient()
        }

        return this.webClient!!
    }

    @JvmStatic
    fun makeRequest(request: Request): Response {
        log.debug("Web is make request to: {} with method: {}", request.url, request.method)
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