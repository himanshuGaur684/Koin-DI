package gaur.himanshu.koindependencyinjection.data

import gaur.himanshu.koindependencyinjection.domain.AuthRepository
import kotlinx.coroutines.delay

class AuthRepoImpl : AuthRepository {

    override suspend fun getUserName(): String {
        delay(3000)
        return "himanshu"
    }

    override suspend fun getAddress(): String {
        delay(1000)
        return "address"
    }
}