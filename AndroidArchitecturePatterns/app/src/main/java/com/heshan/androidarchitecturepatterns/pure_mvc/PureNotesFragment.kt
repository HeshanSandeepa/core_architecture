package com.heshan.androidarchitecturepatterns.pure_mvc

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


class PureNotesFragment : Fragment() {

    private var _binding: MvcFragmentNotesBinding? = null
    private var _recycleView: RecyclerView? = null
    private lateinit var noteRepository: PureNoteRepository

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

        binding.button2.setOnClickListener(View.OnClickListener {

            activity?.runOnUiThread {
                noteRepository.addNote(PureNoteRepository.Note(id = 15, note = "hello Samokle"))
            }
        })

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getNotesAndUpdateUi() {
        val pureNoteAdapter = PureNoteAdapter(PureNoteRepository().getNotes())
        recycleView?.adapter = pureNoteAdapter
        recycleView?.layoutManager = LinearLayoutManager(activity)
        recycleView?.setHasFixedSize(true)
        pureNoteAdapter.notifyDataSetChanged()
    }
}