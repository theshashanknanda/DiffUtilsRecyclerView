package com.example.kotlinnotesapp.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun dao(): NoteDao

    companion object{
        private val INSTANCE: NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): NoteDatabase{
            synchronized(LOCK){
                return INSTANCE ?: createDatabase(context)
            }
        }

        private fun createDatabase(context: Context): NoteDatabase {
            return Room.databaseBuilder(context, NoteDatabase::class.java, "userdb").allowMainThreadQueries().build()
        }
    }
}
