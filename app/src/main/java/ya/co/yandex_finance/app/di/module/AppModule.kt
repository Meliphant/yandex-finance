package ya.co.yandex_finance.app.di.module

import dagger.Module
import dagger.Provides
import ya.co.yandex_finance.app.App
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideApp() = app
}
