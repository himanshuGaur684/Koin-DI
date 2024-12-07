package gaur.himanshu.koindependencyinjection.data

import gaur.himanshu.koindependencyinjection.data.repository.AuthRepoImpl
import gaur.himanshu.koindependencyinjection.domain.repository.AuthRepository
import org.koin.dsl.module

val dataModule = module {

    factory<AuthRepository> { AuthRepoImpl() }

}