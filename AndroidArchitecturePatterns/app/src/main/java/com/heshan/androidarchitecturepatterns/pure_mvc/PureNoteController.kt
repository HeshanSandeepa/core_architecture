package com.heshan.androidarchitecturepatterns.pure_mvc

class PureNoteController(private val notesFragment: PureNotesFragment) {

    private val noteRepo get() = PureNoteRepository()

    fun getNotes() {
        val notes = noteRepo.getNotes()
        notesFragment.updateUi()
    }

}