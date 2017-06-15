package eder.padilla.kotlinandroidcourse.noteapp.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import eder.padilla.kotlinandroidcourse.R
import eder.padilla.kotlinandroidcourse.Util
import eder.padilla.kotlinandroidcourse.noteapp.adapter.DBManager
import eder.padilla.kotlinandroidcourse.noteapp.adapter.NotesAdapter
import eder.padilla.kotlinandroidcourse.noteapp.adapter.OnNoteSelected
import eder.padilla.kotlinandroidcourse.noteapp.model.Note
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() , OnNoteSelected {

    var notesList = ArrayList<Note>()

    val notesAdapter = NotesAdapter(notesList,this@NoteActivity,this@NoteActivity)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        initBasics()
        loadFromQuery("%")
    }

    private fun loadFromQuery(title : String) {
        var dbManager = DBManager(this)
        var selectionArgs = arrayOf(title)
        val cursor = dbManager.query(null!!,"Title like ? and name like ?",selectionArgs,"Title")
    }

    private fun initBasics() {
        notesList.add(Note(1,"Eder nota 1","Hola mundo"))
        notesList.add(Note(2,"Eder nota 2","Hola mundo 2"))
        notesList.add(Note(3,"Eder nota 3","Hola mundo 3"))
        recView.layoutManager = LinearLayoutManager(this@NoteActivity, LinearLayoutManager.VERTICAL, false)
        recView.setHasFixedSize(true)
        recView.adapter=notesAdapter
        notesAdapter.notifyDataSetChanged()
    }

    override fun onNoteSelected(note: Note) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        val sv:SearchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.addNote->{
                val intent = Intent(this@NoteActivity,AddNoteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
