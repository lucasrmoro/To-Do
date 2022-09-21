package br.com.lucas.todo.core.di

import android.content.Context
import androidx.room.Room
import br.com.lucas.todo.database.AppDataBase
import br.com.lucas.todo.ui.base.DummyViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun provideRoomDB(context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, "todo-db").build().taskDao()

    @Singleton
    @Provides
    fun provideDummyViewModel() = DummyViewModel()
}