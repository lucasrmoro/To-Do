package br.com.tasks.core.di

import android.content.Context
import androidx.room.Room
import br.com.core.util.DateUtil
import br.com.tasks.data.db.dao.AppDataBase
import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.data.db.repository.TaskRepository
import br.com.lucas.todo.data.db.repository.TaskRepositoryImpl
import br.com.tasks.domain.mappers.TaskMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TasksModule {

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
    fun provideTaskEntityMapper(dateUtil: DateUtil): TaskMapper = TaskMapper(dateUtil)

}