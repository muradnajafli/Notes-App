package com.muradnajafli.notesapp.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muradnajafli.notesapp.presentation.NoteViewModel
import androidx.navigation.fragment.findNavController
import com.muradnajafli.notesapp.R
import com.muradnajafli.notesapp.databinding.FragmentNoteListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()
    private val notesAdapter by lazy { NotesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupNavController()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = notesAdapter
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.noteList.collect { notes ->
                notesAdapter.setData(notes)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                binding.errorMessage.isVisible = true
                binding.errorMessage.text = message.asString(requireContext())
            } else {
                binding.errorMessage.isVisible = false
            }
        }
    }

    private fun setupNavController() {
        binding.createNoteButton.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


