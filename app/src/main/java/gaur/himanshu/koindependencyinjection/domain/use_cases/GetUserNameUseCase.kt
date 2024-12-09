package gaur.himanshu.koindependencyinjection.domain.use_cases

import gaur.himanshu.koindependencyinjection.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetUserNameUseCase(
    private val authRepository: Lazy<AuthRepository>
) {

    operator fun invoke() = flow<String> {
        emit(authRepository.value.getUserName())
    }.catch {
        emit("error")
    }.flowOn(Dispatchers.IO)

}