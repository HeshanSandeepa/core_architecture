package com.heshan.androidarchitecturepatterns.mvc

class NoteRepository {

    class Note(val id: Int, private val note: String) {
        var noteString: String = ""
            get() = this.note
    }

    companion object {

        private var noteDataBase: MutableList<Note> = mutableListOf<Note>()

        fun addNote(note: Note) {
            noteDataBase.add(note)
        }

        fun getNotes(): List<Note> {
            return  noteDataBase
        }
    }

}