package dev.rafaelvindelamor.domain

class RegisterService(private val repository: UserRepository) {

    fun register(request: UserRequest): User {
        return repository.loadByName(request.name)
            .fold({ repository.save(User(name = request.name)) }, { user -> user })
    }
}
