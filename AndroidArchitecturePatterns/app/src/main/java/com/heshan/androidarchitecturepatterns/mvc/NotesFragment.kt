package com.heshan.androidarchitecturepatterns.mvc

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidarchitecturepatterns.R
import com.heshan.androidarchitecturepatterns.databinding.MvcFragmentNotesBinding
import java.util.*


class NotesFragment : Fragment()  {

    private var _binding: MvcFragmentNotesBinding? = null
    private var _recycleView: RecyclerView? = null
    private lateinit var noteRepository: NoteRepository

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val recycleView get() = _recycleView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.mvc_fragment_notes,
            container,
            false
        )
        _recycleView = binding.mvcRecycleView

        //noteRepository.addNote(PureNoteRepository.Note(id = 15, note = "hello Samokle"))

        binding.button2.setOnClickListener(View.OnClickListener {

            activity?.runOnUiThread {
                noteRepository.addNote(NoteRepository.Note(id = 15, note = "hello Samokle"))
            }
        })

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getNotesAndUpdateUi() {

            val noteAdapter = NoteAdapter(NoteRepository().getNotes())
            recycleView?.adapter = noteAdapter
            recycleView?.layoutManager = LinearLayoutManager(activity)
            recycleView?.setHasFixedSize(true)
            noteAdapter.notifyDataSetChanged()


    }
}


// https://www.raywenderlich.com/books/advanced-android-app-architecture/v1.0/chapters/2-model-view-controller-theory

// try traditional MVC