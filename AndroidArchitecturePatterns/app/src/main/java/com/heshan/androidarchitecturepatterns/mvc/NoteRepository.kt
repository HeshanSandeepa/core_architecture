package com.heshan.androidarchitecturepatterns.mvc

import java.util.*

class NoteRepository: Observable() {

    class Note(val id: Int, private val note: String) {
        val noteString: String
            get() = this.note
    }

    fun addNote(note: Note) {
        noteDataBase.add(note)
        notifyObservers()
    }

    fun getNotes(): List<Note> {
        return  noteDataBase
    }

    companion object {
        private var noteDataBase = mutableListOf<Note>()
    }

}