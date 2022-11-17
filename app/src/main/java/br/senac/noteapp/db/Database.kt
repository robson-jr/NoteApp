package br.senac.noteapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.senac.noteapp.model.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class Database: RoomDatabase() {
    abstract fun  noteDao(): NoteDao

}