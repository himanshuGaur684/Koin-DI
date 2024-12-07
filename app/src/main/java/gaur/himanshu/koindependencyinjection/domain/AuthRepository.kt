package gaur.himanshu.koindependencyinjection.domain

interface AuthRepository {

    suspend fun getUserName():String

}