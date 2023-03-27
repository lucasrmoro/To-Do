package br.com.core.di

import android.app.Application
import android.content.Context
import br.com.core.providers.ResourcesProviderImpl
import br.com.core.providers.ResourcesProviderInterface
import br.com.core.util.DateUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun provideResourcesProvider(application: Application): ResourcesProviderInterface =
        ResourcesProviderImpl(application.resources)

    @Singleton
    @Provides
    fun provideDateUtil(resourcesProviderInterface: ResourcesProviderInterface) = DateUtil(resourcesProviderInterface)

}