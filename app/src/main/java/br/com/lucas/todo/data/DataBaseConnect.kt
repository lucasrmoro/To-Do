package br.com.lucas.todo.data

import android.content.Context
import androidx.room.Room
import br.com.lucas.todo.data.db.dao.AppDataBase

object DataBaseConnect {
    fun getTaskDao(context: Context) = Room.databaseBuilder(
        context,
        AppDataBase::class.java, "todo-db"
    ).build().taskDao()
}