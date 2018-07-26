package ya.co.yandex_finance.di.module


import dagger.Module
import dagger.Provides
import ya.co.yandex_finance.di.App
import javax.inject.Singleton

@Module class AppModule(val app: App) {
    @Provides @Singleton fun provideApp() = app
}