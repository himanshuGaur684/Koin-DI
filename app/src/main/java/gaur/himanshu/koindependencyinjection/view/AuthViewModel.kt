package gaur.himanshu.koindependencyinjection.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gaur.himanshu.koindependencyinjection.domain.GetUserNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AuthViewModel(
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserName()
    }

    private fun getUserName() = getUserNameUseCase()
        .onEach { result ->
            _uiState.update { UiState(data = result) }
        }.catch { error ->
            _uiState.update { UiState(error = error.message.toString()) }
        }.onStart {
            _uiState.update { UiState(isLoading = true) }
        }.launchIn(viewModelScope)

}

data class UiState(
    val isLoading: Boolean = false,
    val data: String? = null,
    val error: String = ""
)