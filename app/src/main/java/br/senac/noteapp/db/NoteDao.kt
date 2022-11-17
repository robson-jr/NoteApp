package br.senac.noteapp.db

import androidx.room.*
import br.senac.noteapp.model.Note

@Dao
interface NoteDao {

    @Insert
    fun inserir(note: Note)

    @Query("SELECT * FROM Note")
    fun listar(): List<Note>



}