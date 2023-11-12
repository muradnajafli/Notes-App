package com.muradnajafli.notesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.muradnajafli.notesapp.R
import com.muradnajafli.notesapp.data.Note
import com.muradnajafli.notesapp.data.NoteViewModel
import com.muradnajafli.notesapp.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        binding.saveButton.setOnClickListener {
            val currentDate = Date()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val formattedDate = dateFormat.format(currentDate)

            val note = Note(0,
                binding.nameInput.editText?.text.toString(),
                binding.noteInput.editText?.text.toString(),
                formattedDate
            )
            addNote(note)

            findNavController().navigate(R.id.action_addFragment_to_noteListFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addNote(note: Note) {
        viewModel.addNote(note)
        logFirebaseEvent()
    }

    private fun logFirebaseEvent() {
        val bundle = Bundle().apply {
            putInt("add_note_content", 1)
        }
        firebaseAnalytics.logEvent("add_note", bundle)
    }
}
