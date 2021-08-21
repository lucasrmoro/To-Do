package br.com.lucas.santanderbootcamp.todolist.database

import android.content.Context
import androidx.room.Room

object DataBaseConnect {
    fun getTaskDao(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, "todo-db"
    ).build().taskDao()
}