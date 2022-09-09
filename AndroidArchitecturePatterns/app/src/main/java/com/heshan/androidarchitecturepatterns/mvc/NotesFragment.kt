package com.heshan.androidarchitecturepatterns.mvc

import android.os.Bundle
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


class NotesFragment : Fragment(), Observer {

    private var _binding: MvcFragmentNotesBinding? = null
    private var _recycleView: RecyclerView? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val recycleView get() = _recycleView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribe()
    }

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



        //getNotesAndUpdateUi()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //getNotesAndUpdateUi()
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
        val noteRepository =  NoteRepository()
        noteRepository.addObserver(this)
    }

    /*
    * Update UI from model
    * Loosely coupled via Observer pattern
    *
    * */
    override fun update(p0: Observable?, p1: Any?) {
        getNotesAndUpdateUi()
    }
}
