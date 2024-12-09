package gaur.himanshu.koindependencyinjection.domain

import gaur.himanshu.koindependencyinjection.domain.repository.AuthRepository
import gaur.himanshu.koindependencyinjection.domain.use_cases.GetUserNameUseCase
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
class DomainModule {

    @Factory
    fun provideUserNameUseCase(authRepository: Lazy<AuthRepository>): GetUserNameUseCase {
        return GetUserNameUseCase(authRepository)
    }

}