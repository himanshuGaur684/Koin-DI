package gaur.himanshu.koindependencyinjection.view

import gaur.himanshu.koindependencyinjection.MainActivity
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Scope

data class SessionManager(
    val session: String
)

@Module
class SessionModule {

    @Factory
    @Scope(MainActivity::class)
    fun provideSessionManager(): SessionManager {
        return SessionManager("this is second session")
    }

}