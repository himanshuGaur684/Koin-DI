package gaur.himanshu.koindependencyinjection.domain.use_cases

import gaur.himanshu.koindependencyinjection.domain.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAddressUseCase(private val authRepository: AuthRepository) {

    operator fun invoke() = flow<String> {
        emit(authRepository.getAddress())
    }.catch { throwable ->
        emit(throwable.localizedMessage)
    }.flowOn(Dispatchers.IO)
}