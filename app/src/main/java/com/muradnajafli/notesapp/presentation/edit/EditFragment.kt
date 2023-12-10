package com.muradnajafli.notesapp.presentation.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.muradnajafli.notesapp.R
import com.muradnajafli.notesapp.data.model.Note
import com.muradnajafli.notesapp.presentation.NoteViewModel
import com.muradnajafli.notesapp.databinding.FragmentEditBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by viewModels()
    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        binding.name.setText(args.editNote.name)
        binding.note.setText(args.editNote.note)

        binding.saveButton.setOnClickListener {
            val formattedDate = getCurrentFormattedDate()

            val note = Note(args.editNote.id,
                binding.nameInput.editText?.text.toString(),
                binding.noteInput.editText?.text.toString(),
                formattedDate
            )
            findNavController().navigate(R.id.action_editFragment_to_noteListFragment)
            viewModel.updateNote(note)
        }

        return binding.root
    }

    private fun getCurrentFormattedDate(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}