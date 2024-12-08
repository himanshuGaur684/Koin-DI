package gaur.himanshu.koindependencyinjection.view

import gaur.himanshu.koindependencyinjection.MainActivity
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

data class User(val name: String)

val UserScope = named("userScope")

val userModule = module {
    scope(UserScope) {
        scoped {
            User("gaur")
        }
    }
}


data class SessionManager(
    val session: String
)

val sessionModule = module {
    scope<MainActivity> {
        scoped {
            SessionManager(session = "this is session")
        }
    }
}


val uiModule = module {

    viewModel { AuthViewModel(get(), get()) }

    factory<String> { "first string" }

    factory<String> { "second string" }

}