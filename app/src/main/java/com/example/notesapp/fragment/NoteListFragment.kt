package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.data.NoteViewModel
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var viewModel: NoteViewModel
    private lateinit var noteList: List<Note>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        noteList = ArrayList()
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        notesAdapter = NotesAdapter(requireContext(), noteList)
        binding.recyclerView.adapter = notesAdapter

        binding.createNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addFragment)
        }

        viewModel.readAllData.observe(viewLifecycleOwner) { noteList ->
            notesAdapter.setData(noteList)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


