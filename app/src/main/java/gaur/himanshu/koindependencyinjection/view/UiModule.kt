package gaur.himanshu.koindependencyinjection.view

import gaur.himanshu.koindependencyinjection.MainActivity
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

data class SessionManager(
    val session: String
)

@Module
@ComponentScan()
class UiModule {

    @Scope(MainActivity::class)
    @Scoped
    fun provideSessoinManager(): SessionManager {
        return SessionManager("this is session")
    }

    @Scope(UserScope::class)
    @Scoped
    fun provideUser(): User {
        return User(name = "Himanshu Gaur")
    }

    @Factory
    fun provideString(): String {
        return "first string"
    }

    @Factory
    fun provideSecondSTring(): String {
        return "second string"
    }

}

