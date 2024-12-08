package gaur.himanshu.koindependencyinjection

import android.app.Application
import gaur.himanshu.koindependencyinjection.data.dataModule
import gaur.himanshu.koindependencyinjection.domain.domainModule
import gaur.himanshu.koindependencyinjection.view.sessionModule
import gaur.himanshu.koindependencyinjection.view.uiModule
import gaur.himanshu.koindependencyinjection.view.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(
                dataModule,
                domainModule,
                uiModule,
                sessionModule,
                userModule
            )
        }
    }

}