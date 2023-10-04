package com.example.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.NoteViewModel
import com.example.notesapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        val note = args.detailNote
        binding.nameDetail.text = note.name
        binding.noteDetail.text = note.note


        binding.editButton.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(note)
            it.findNavController().navigate(action)
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteNote(note)
            findNavController().navigate(R.id.action_detailFragment_to_noteListFragment)
        }

        return binding.root
    }

}