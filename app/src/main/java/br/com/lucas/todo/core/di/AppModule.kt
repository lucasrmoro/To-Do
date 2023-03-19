package br.com.lucas.todo.core.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import br.com.lucas.todo.core.providers.date.DateProvider
import br.com.lucas.todo.core.providers.date.DateProviderImpl
import br.com.lucas.todo.core.providers.resources.ResourcesProvider
import br.com.lucas.todo.core.providers.resources.ResourcesProviderImpl
import br.com.lucas.todo.data.db.dao.AppDataBase
import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.data.db.repository.TaskRepositoryImpl
import br.com.lucas.todo.domain.mappers.TaskMapper
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
        Room.databaseBuilder(context, AppDataBase::class.java, "todo-db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideTaskDao(appDataBase: AppDataBase) = appDataBase.taskDao()

    @Singleton
    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository = TaskRepositoryImpl(taskDao)

    @Singleton
    @Provides
    fun provideResourcesProvider(application: Application): ResourcesProvider =
        ResourcesProviderImpl(application.resources)

    @Singleton
    @Provides
    fun provideDateUtil(resourcesProvider: ResourcesProvider): DateProvider =
        DateProviderImpl(resourcesProvider)

    @Singleton
    @Provides
    fun provideTaskEntityMapper(dateProvider: DateProvider): TaskMapper = TaskMapper(dateProvider)

}