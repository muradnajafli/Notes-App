package com.muradnajafli.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.muradnajafli.notesapp.adapter.NotesAdapter
import com.muradnajafli.notesapp.data.NoteViewModel
import androidx.navigation.fragment.findNavController
import com.muradnajafli.notesapp.R
import com.muradnajafli.notesapp.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesAdapter: NotesAdapter
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpNavController()
        observeData()
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        notesAdapter = NotesAdapter()
        binding.recyclerView.adapter = notesAdapter
    }

    private fun observeData() {
        viewModel.readAllData.observe(viewLifecycleOwner) { noteList ->
            notesAdapter.setData(noteList)
        }
    }

    private fun setUpNavController() {
        binding.createNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


