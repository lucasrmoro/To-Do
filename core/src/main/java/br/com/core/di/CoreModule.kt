package br.com.core.di

import br.com.core.providers.ResourcesProviderImpl
import br.com.core.providers.ResourcesProviderInterface
import br.com.core.util.DateUtil
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {
    single<ResourcesProviderInterface> { ResourcesProviderImpl(androidApplication().resources) }
    single { DateUtil(get()) }
}