package gaur.himanshu.koindependencyinjection

import android.app.Application
import gaur.himanshu.koindependencyinjection.data.DataModule
import gaur.himanshu.koindependencyinjection.domain.DomainModule
import gaur.himanshu.koindependencyinjection.view.ViewModelModule
import gaur.himanshu.koindependencyinjection.view.viewModule
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
                defaultModule,
                DataModule().module,
                DomainModule().module,
                ViewModelModule().module
            )
        }
    }

}