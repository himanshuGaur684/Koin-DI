package gaur.himanshu.koindependencyinjection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gaur.himanshu.koindependencyinjection.ui.theme.KoinDependencyInjectionTheme
import gaur.himanshu.koindependencyinjection.view.AuthViewModel
import gaur.himanshu.koindependencyinjection.view.SessionManager
import gaur.himanshu.koindependencyinjection.view.SessionModule
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module


class MainActivity : ComponentActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()
    private val sessionManager: SessionManager by scope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(SessionModule().module)
        enableEdgeToEdge()
        setContent {
            KoinDependencyInjectionTheme {
                val viewModel = koinViewModel<AuthViewModel>()
                val uiState = viewModel.uiState.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (uiState.value.isLoading) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    if (uiState.value.error.isNullOrBlank().not()) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(uiState.value.error.toString())
                        }
                    }
                    uiState.value.data?.let {
                        Greeting(
                            name = it.plus(sessionManager.session),
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        unloadKoinModules(SessionModule().module)
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KoinDependencyInjectionTheme {
        Greeting("Android")
    }
}