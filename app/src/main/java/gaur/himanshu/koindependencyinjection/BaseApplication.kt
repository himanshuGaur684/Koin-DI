package gaur.himanshu.koindependencyinjection

import android.app.Application
import gaur.himanshu.koindependencyinjection.data.DataModule
import gaur.himanshu.koindependencyinjection.domain.DomainModule
import gaur.himanshu.koindependencyinjection.view.UiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                DataModule().module,
                DomainModule().module,
                UiModule().module,
                defaultModule
            )
        }
    }

}