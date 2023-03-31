package br.com.tasks.core.di

import androidx.room.Room
import br.com.tasks.data.db.repository.TaskRepository
import br.com.tasks.data.db.repository.TaskRepositoryImpl
import br.com.tasks.data.db.dao.AppDataBase
import br.com.tasks.domain.mappers.TaskMapper
import br.com.tasks.domain.useCases.*
import br.com.tasks.presentation.edit.EditTaskViewModel
import br.com.tasks.presentation.list.ListTaskViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val viewModels = module {
    viewModel { ListTaskViewModel(get(), get(), get(), get()) }
    viewModel { EditTaskViewModel(get(), get()) }
}

val useCases = module {
    single { DeleteSelectedTasksUseCase(get()) }
    single { DeleteTaskUseCase(get(), get()) }
    single { GetAllTasksUseCase(get(), get()) }
    single { GetTasksByDateUseCase(get()) }
    single { GetTasksCategorizedByDateUseCase(get(), get()) }
    single { InsertTaskUseCase(get(), get()) }
    single { UpdateTaskUseCase(get(), get()) }
}

val mappers = module {
    single { TaskMapper(get()) }
}

val repositories = module {
    single<TaskRepository> { TaskRepositoryImpl(get()) }
}

val daos = module {
    single { get<AppDataBase>().taskDao() }
}

val database = module {
    single {
        Room.databaseBuilder(get(), AppDataBase::class.java, "todo-db")
            .fallbackToDestructiveMigration()
            .build()
    }
}

object TasksInitializer {
    fun init() = loadKoinModules(listOf(database, daos, repositories, mappers, useCases, viewModels))
}