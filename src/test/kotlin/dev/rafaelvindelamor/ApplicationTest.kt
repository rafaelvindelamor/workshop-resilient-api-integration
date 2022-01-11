package dev.rafaelvindelamor

import com.google.gson.Gson
import dev.rafaelvindelamor.domain.User
import dev.rafaelvindelamor.domain.UserRequest
import dev.rafaelvindelamor.plugins.configureRouting
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ApplicationTest {
    @Test
    fun testHelloWorld() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello World!", response.content)
            }
        }
    }

    @Test
    fun testRegisterUser() {
        withTestApplication({ configureRouting() }) {
            val gson = Gson()
            handleRequest(HttpMethod.Post, "/users") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                val body = gson.toJson(UserRequest("test"))
                setBody(body)
            }.apply {
                val result = gson.fromJson(response.content, User::class.java)
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotNull(result.id)
                assertEquals("test", result.name)
            }
        }
    }
}
