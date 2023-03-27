package br.com.tasks.data.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.lucas.todo.data.db.dao.TaskDao
import br.com.lucas.todo.data.db.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 6)
abstract class AppDataBase : RoomDatabase() {
    abstract fun taskDao() : TaskDao
}