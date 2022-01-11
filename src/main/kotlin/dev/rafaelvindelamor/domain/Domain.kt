package dev.rafaelvindelamor.domain

import java.util.*

data class UserRequest(val name: String)
data class User(val id: UUID = UUID.randomUUID(), val name: String)
