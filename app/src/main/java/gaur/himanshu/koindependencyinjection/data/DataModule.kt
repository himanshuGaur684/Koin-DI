package gaur.himanshu.koindependencyinjection.data

import gaur.himanshu.koindependencyinjection.domain.AuthRepository
import org.koin.dsl.module

val dataModule = module {

    factory<AuthRepository> { AuthRepoImpl() }


}