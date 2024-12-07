package gaur.himanshu.koindependencyinjection.domain.repository

interface AuthRepository {

    suspend fun getUserName(): String

}