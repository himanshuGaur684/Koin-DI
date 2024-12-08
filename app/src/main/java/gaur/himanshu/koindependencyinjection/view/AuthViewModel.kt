package gaur.himanshu.koindependencyinjection.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gaur.himanshu.koindependencyinjection.domain.use_cases.GetAddressUseCase
import gaur.himanshu.koindependencyinjection.domain.use_cases.GetUserNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class AuthViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val getAddressUseCase: GetAddressUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserName()
    }

    fun getUserName() = getUserNameUseCase()
        .onStart {
            _uiState.update { UiState(isLoading = true) }
        }.onEach { result ->
            _uiState.update { UiState(name = result) }
        }.catch { message ->
            _uiState.update { UiState(error = message.toString()) }
        }.launchIn(viewModelScope)

    fun getAddress() = getAddressUseCase()
        .onEach { result ->
            _uiState.update { UiState(address = result) }
        }.catch { message ->
            _uiState.update { UiState(error = message.toString()) }
        }.launchIn(viewModelScope)

}

data class UiState(
    val isLoading: Boolean = false,
    val name: String? = null,
    val address: String? = null,
    val error: String = ""
)