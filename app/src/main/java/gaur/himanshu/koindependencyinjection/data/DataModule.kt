package gaur.himanshu.koindependencyinjection.data

import gaur.himanshu.koindependencyinjection.domain.AuthRepository
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Singleton

@Module
class DataModule {

    @Factory
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Singleton
    @Named("first")
    fun provideString(): String {
        return "first string"
    }

    @Singleton
    @Named("second")
    fun provideSecondString(): String {
        return "second string"
    }

}