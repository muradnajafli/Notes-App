package com.muradnajafli.notesapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.analytics.FirebaseAnalytics
import com.muradnajafli.notesapp.R
import com.muradnajafli.notesapp.presentation.NoteViewModel
import com.muradnajafli.notesapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext().applicationContext)

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
            logFirebaseEvent()
        }

        return binding.root
    }

    private fun logFirebaseEvent() {
        val bundle = Bundle().apply {
            putInt("remove_note_content", 1)
        }
        firebaseAnalytics.logEvent("remove_note", bundle)
    }

}