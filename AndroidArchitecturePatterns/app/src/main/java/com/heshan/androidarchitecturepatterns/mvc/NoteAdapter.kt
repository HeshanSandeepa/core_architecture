package com.heshan.androidarchitecturepatterns.mvc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidarchitecturepatterns.databinding.MvcViewHolderViewBinding

class NoteAdapter(private val notes: List<NoteRepository.Note>):
    RecyclerView.Adapter<NoteAdapter.Companion.NoteViewHolder>() {

    private lateinit var binding: MvcViewHolderViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        binding = MvcViewHolderViewBinding
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

        class NoteViewHolder(private val binding: MvcViewHolderViewBinding)
            : RecyclerView.ViewHolder(binding.root) {

            fun  binding(note: NoteRepository.Note) {
                binding.note.text = note.noteString
            }
        }
    }
}