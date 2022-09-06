package com.heshan.androidarchitecturepatterns.mvc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.heshan.androidarchitecturepatterns.R
import com.heshan.androidarchitecturepatterns.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NotesFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_second, container, false)
        val recyclerView = binding.mvcRecycleView
        val noteAdapter = NoteAdapter(NoteRepository.getNotes())

        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager  = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}