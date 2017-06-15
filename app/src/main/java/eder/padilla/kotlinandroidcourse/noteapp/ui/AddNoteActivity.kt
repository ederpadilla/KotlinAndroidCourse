package eder.padilla.kotlinandroidcourse.noteapp.ui

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import eder.padilla.kotlinandroidcourse.R
import eder.padilla.kotlinandroidcourse.noteapp.adapter.DBManager
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }

    fun addNote(view : View){
        var dbManager = DBManager(this)
        var values= ContentValues()
        values.put("Title",etTitle.text.toString())
        values.put("Description", etDescription.text.toString())
        val Id = dbManager.insert(values)
        if (Id>0){
            Toast.makeText(this," note is added ",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this," note can not be added ",Toast.LENGTH_SHORT).show()
        }

    }
}
