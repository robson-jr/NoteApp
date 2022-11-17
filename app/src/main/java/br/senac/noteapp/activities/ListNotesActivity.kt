package br.senac.noteapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.senac.noteapp.databinding.ActivityListNotesBinding
import br.senac.noteapp.databinding.NotaBinding
import br.senac.noteapp.db.Database
import br.senac.noteapp.model.Note

class ListNotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityListNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val i = Intent(this, NewNoteActivity::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()

        listarNotas()
    }

    fun listarNotas(){
        val db = Room.databaseBuilder( this, Database::class.java, "notes").build()

        Thread{
           val notas = db.noteDao().listar()
            runOnUiThread{
                atualizarTela(notas)
            }

        }.start()
    }

    fun atualizarTela(notas:List<Note>){
        //0 - remover tudo do container
        binding.container.removeAllViews()

        //para cada nota:
        notas.forEach{
            val notaBinding = NotaBinding.inflate(layoutInflater)

            //2 - modificar o conteudo do layout
            notaBinding.textTitulo.text = it.title
            notaBinding.textDesc.text =  it.desc

            //3 - jogar a nota no container
            binding.container.addView(notaBinding.root)
        }
    }
}