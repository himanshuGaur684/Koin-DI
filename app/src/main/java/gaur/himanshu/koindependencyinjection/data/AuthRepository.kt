package gaur.himanshu.koindependencyinjection.data

import gaur.himanshu.koindependencyinjection.domain.AuthRepository
import kotlinx.coroutines.delay

class AuthRepositoryImpl : AuthRepository {
    override suspend fun getUserName(): String {
        delay(2000)
        return "Himanshu Gaur"
    }
}