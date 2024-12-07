package gaur.himanshu.koindependencyinjection.domain

import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
class DomainModule {

    @Factory
    fun provideGetUserNameUseCase(authRepository: AuthRepository): GetUserNameUseCase {
        return GetUserNameUseCase(authRepository)
    }

}