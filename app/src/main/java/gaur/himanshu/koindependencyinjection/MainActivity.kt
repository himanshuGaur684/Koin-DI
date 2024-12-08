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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gaur.himanshu.koindependencyinjection.ui.theme.KoinDependencyInjectionTheme
import gaur.himanshu.koindependencyinjection.view.AuthViewModel
import gaur.himanshu.koindependencyinjection.view.SessionManager
import gaur.himanshu.koindependencyinjection.view.User
import gaur.himanshu.koindependencyinjection.view.UserScope
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


class MainActivity : ComponentActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()

    private val sessionManager: SessionManager by inject()

    private lateinit var userScope: Scope
    private lateinit var user: User


    private val firstString: String by inject<String>()
    private val secondString: String by inject<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userScope = getKoin().createScope("scope_id", UserScope)
        user = userScope.get()

        enableEdgeToEdge()
        setContent {
            KoinDependencyInjectionTheme {

                val viewModel = getViewModel<AuthViewModel>()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    if (uiState.isLoading) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    if (uiState.error.isNotBlank()) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(uiState.error)
                        }
                    }
                    uiState.name?.let {
                        Greeting(
                            name = it.plus(firstString).plus(secondString),
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        onCloseScope()
        userScope.close()
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