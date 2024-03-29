package br.com.lucas.todo.core.di

import android.content.Context
import androidx.room.Room
import br.com.lucas.todo.data.db.dao.AppDataBase
import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.domain.mappers.TaskMapper
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.data.db.repository.TaskRepositoryImpl
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
        Room.databaseBuilder(context, AppDataBase::class.java, "todo-db").build()

    @Singleton
    @Provides
    fun provideTaskDao(appDataBase: AppDataBase) = appDataBase.taskDao()

    @Singleton
    @Provides
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository = TaskRepositoryImpl(taskDao)

    @Singleton
    @Provides
    fun provideTaskEntityMapper(): TaskMapper = TaskMapper()
}