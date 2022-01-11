package dev.rafaelvindelamor.domain

import arrow.core.Option

interface UserRepository {
    fun save(user: User): User
    fun loadByName(name: String): Option<User>
}