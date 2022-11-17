package br.senac.noteapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.senac.noteapp.databinding.ActivityNewNoteBinding
import br.senac.noteapp.db.Database
import br.senac.noteapp.model.Note

class NewNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {
            inserirNota()
        }
    }
    fun inserirNota(){
        //1- criar uma instancia no Room
        val db = Room.databaseBuilder( this, Database::class.java, "notes").build()

        //2 - Carregar os parametros(se tiver)

        val title = binding.etTitle.text.toString()
        val descricao = binding.etDesc.text.toString()

        val nota = Note(title = title, desc = descricao)

        //3- obter e executar a funcao do dao
        Thread{
            db.noteDao().inserir(nota)
            finish()
        }.start()


    }

}