package gaur.himanshu.koindependencyinjection.data

import gaur.himanshu.koindependencyinjection.data.repository.AuthRepoImpl
import gaur.himanshu.koindependencyinjection.domain.repository.AuthRepository
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
class DataModule {


    @Factory
    fun provideAuthRepository(): AuthRepository {
        return AuthRepoImpl()
    }

}