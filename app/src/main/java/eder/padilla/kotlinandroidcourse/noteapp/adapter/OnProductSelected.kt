package eder.padilla.kotlinandroidcourse.noteapp.adapter

import eder.padilla.kotlinandroidcourse.noteapp.model.Note


/**
 * Created by ederpadilla on 10/01/17.
 */

interface OnNoteSelected {
    fun onNoteSelected(note: Note)
}
