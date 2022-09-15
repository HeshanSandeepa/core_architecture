package com.heshan.androidarchitecturepatterns.pure_mvc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidarchitecturepatterns.databinding.MvcNoteViewHolderBinding

class PureNoteAdapter(private val notes: List<PureNoteRepository.Note>):
    RecyclerView.Adapter<PureNoteAdapter.Companion.NoteViewHolder>() {

    private lateinit var binding: MvcNoteViewHolderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = MvcNoteViewHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding(note)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    companion object {
        class NoteViewHolder(private val binding: MvcNoteViewHolderBinding)
            : RecyclerView.ViewHolder(binding.root) {

            fun  binding(note: PureNoteRepository.Note) {
                binding.note.text = note.noteString
            }
        }
    }
}