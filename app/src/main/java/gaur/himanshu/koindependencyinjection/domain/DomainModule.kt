package gaur.himanshu.koindependencyinjection.domain

import gaur.himanshu.koindependencyinjection.domain.use_cases.GetAddressUseCase
import gaur.himanshu.koindependencyinjection.domain.use_cases.GetUserNameUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetUserNameUseCase(get()) }
    factory { GetAddressUseCase(get()) }
}