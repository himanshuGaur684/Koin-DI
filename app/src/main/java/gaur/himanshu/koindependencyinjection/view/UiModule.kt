package gaur.himanshu.koindependencyinjection.view

import gaur.himanshu.koindependencyinjection.MainActivity
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

data class SessionManager(
    val session: String
)

data class User(
    val name: String
)

val UserScope = named("UserScope")

val userModule = module {
    scope(UserScope) {
        scoped {
            User(name = "Gaur")
        }
    }

    factory<String>(
        named("first")
    ) { "first" }

    factory<String>(named("second")) { "second" }
}

val sessionModule = module {
    scope<MainActivity> {
        scoped {
            SessionManager(session = "this is activity")
        }
    }
}

val uiModule = module {
    viewModel { AuthViewModel(get()) }
}
