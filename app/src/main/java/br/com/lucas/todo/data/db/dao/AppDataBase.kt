package br.com.lucas.todo.data.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.data.db.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}