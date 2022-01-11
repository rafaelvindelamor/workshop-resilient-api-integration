package dev.rafaelvindelamor.database

import arrow.core.Option
import arrow.core.toOption
import dev.rafaelvindelamor.domain.User
import dev.rafaelvindelamor.domain.UserRepository

class InMemoryUserRepository : UserRepository {

    private val database = mutableMapOf<String, User>()

    override fun save(user: User): User {
        return database.computeIfAbsent(user.name) { user }
    }

    override fun loadByName(name: String): Option<User> {
        return database[name].toOption()
    }
}