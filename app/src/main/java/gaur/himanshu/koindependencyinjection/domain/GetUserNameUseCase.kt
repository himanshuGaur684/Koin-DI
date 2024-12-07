package gaur.himanshu.koindependencyinjection.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Provided

class GetUserNameUseCase(
   @Provided private val authRepository: AuthRepository
) {

    operator fun invoke(): Flow<String> = flow {
        emit(authRepository.getUserName())
    }
}