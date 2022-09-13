package com.heshan.androidarchitecturepatterns.mvc

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heshan.androidarchitecturepatterns.R
import com.heshan.androidarchitecturepatterns.databinding.MvcFragmentNotesBinding
import java.util.*


class NotesFragment : Fragment() {

    private var _binding: MvcFragmentNotesBinding? = null
    private var _recycleView: RecyclerView? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val recycleView get() = _recycleView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.mvc_fragment_notes,
            container,
            false)

        _recycleView = binding.mvcRecycleView
        recycleView?.layoutManager = LinearLayoutManager(activity)
        recycleView?.setHasFixedSize(true)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        subscribe()

        val repository = NoteRepository()
        repository.addNote(NoteRepository.Note(id = 15, note = "hello Samokle"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
    * In Android or IOS,  both View & Controller exists in the same file.
    * UI elements work as View & Activity file works as the Controller
    *
    * */

    private fun getNotesAndUpdateUi() {
        val noteAdapter = NoteAdapter(NoteRepository().getNotes())
        recycleView?.adapter = noteAdapter
    }

    private fun subscribe() {

        val navHostFragment: Fragment? =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
        val farg =  navHostFragment?.childFragmentManager?.fragments?.get(0)

        if(farg is NotesFragment){
           Log.e("NotesFragment   ", "NotesFragment")
        } else {
            Log.e("NotesFragment   ", "Not found")

        }

//        val noteRepository =  NoteRepository()
//        noteRepository.addObserver(farg as Observer)
    }

    /*
    * Update UI from model
    * Loosely coupled via Observer pattern
    *
    * */
//    override fun update(p0: Observable?, p1: Any?) {
//        getNotesAndUpdateUi()
//    }
}

// https://www.raywenderlich.com/books/advanced-android-app-architecture/v1.0/chapters/2-model-view-controller-theory