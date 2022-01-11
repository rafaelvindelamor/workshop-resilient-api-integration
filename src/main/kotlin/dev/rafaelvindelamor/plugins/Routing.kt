package dev.rafaelvindelamor.plugins

import dev.rafaelvindelamor.database.InMemoryUserRepository
import dev.rafaelvindelamor.domain.RegisterService
import dev.rafaelvindelamor.domain.UserRequest
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.helloWorld() {
    get("/") {
        call.respondText("Hello World!")
    }
}

fun Route.registerUser(registerService: RegisterService) {
    post("/users") {
        val request = call.receive<UserRequest>()
        val user = registerService.register(request)
        call.respond(user!!)
    }
}

fun Application.configureRouting() {

    val repository = InMemoryUserRepository()
    val registerService = RegisterService(repository)

    routing {
        helloWorld()
        registerUser(registerService)
        install(ContentNegotiation) {
            gson()
        }
    }
}
