package com.example.multiplatform.koru.shared.users

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlin.random.Random


/**
 * We are pretending, that this is some service that connects to some backend and performs some
 * real work asynchronously.
 */
class UserService {

    private val names = listOf(
        "Inigo",
        "Lauryn",
        "Sylvie",
        "Sayed",
        "Chanel",
        "Leo",
        "Seren",
        "Jean",
        "Nigel",
        "Kaitlin"
    )

    private val surnames = listOf(
        "Donovan",
        "Bryan",
        "Perkins",
        "Montes",
        "Kearney",
        "Horton",
        "Neal",
        "Farrow",
        "Bartlett",
        "Mcclure"
    )

    fun getUser(username: String) = searchUser(username)

    suspend fun loadUser(username: String): User? = withContext(Dispatchers.Default) {
        delay(1000)
        searchUser(username)
    }

    fun loadRandomUsers(usersCount: Int) = flow {
        repeat(usersCount) {
            delay(1000)
            emit(randomUser())
        }
    }.flowOn(Dispatchers.Default)

    suspend fun sendUser(user: User) = withContext(Dispatchers.Default) {
        delay(1000)
    }

    private fun randomUser() = User(
        Random.nextInt(100),
        "${names.random()} ${surnames.random()}"
    )

    private fun searchUser(surname: String) = surnames
        .find { surname.isNotEmpty() && it.contains(surname, true) }
        ?.let { User(Random.nextInt(100), "${names.random()} $it") }

}