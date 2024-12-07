package gaur.himanshu.koindependencyinjection.data.repository

import gaur.himanshu.koindependencyinjection.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class AuthRepoImpl : AuthRepository {

    override suspend fun getUserName(): String {
        delay(3000)
        return "Himanshu Gaur"
    }
}