package dev.rafaelvindelamor

import arrow.core.Option
import arrow.core.getOrElse
import dev.rafaelvindelamor.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {

    val port = Option.fromNullable(System.getenv("PORT"))
        .map { value -> Integer.parseInt(value) }
        .getOrElse { 8080 }

    embeddedServer(Netty, port = port, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}
