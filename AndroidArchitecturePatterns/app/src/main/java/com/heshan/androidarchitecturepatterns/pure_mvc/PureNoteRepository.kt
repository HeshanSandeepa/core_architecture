package com.heshan.androidarchitecturepatterns.pure_mvc

import android.util.Log

class PureNoteRepository {

    class Note(val id: Int, private val note: String) {

        val noteString: String
            get() = this.note
    }

    fun addNote(note: Note) {
        noteDataBase.add(note)
        Log.e("Note Repository", getNotes().size.toString())
    }

    fun getNotes(): List<Note> {
        return  noteDataBase
    }

    companion object {
        private var noteDataBase = mutableListOf(
            Note(id = 10, note = "Sample Note"),
            Note(id = 11, note = "Sample Note Two"))
    }

}