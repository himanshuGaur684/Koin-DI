package gaur.himanshu.koindependencyinjection.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gaur.himanshu.koindependencyinjection.domain.use_cases.GetUserNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class AuthViewModel(
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    fun getData() = getUserNameUseCase()
        .onStart { _uiState.update { UiState(isLoading = true) } }
        .catch { throwable ->
            _uiState.update { UiState(error = throwable.localizedMessage) }
        }.onEach { result ->
            _uiState.update { UiState(data = result) }
        }.launchIn(viewModelScope)


}

data class UiState(
    val isLoading: Boolean = false,
    val data: String? = null,
    val error: String = ""
)
