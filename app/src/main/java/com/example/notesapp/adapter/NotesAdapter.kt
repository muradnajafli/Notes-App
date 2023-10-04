package com.example.notesapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.NoteItemBinding
import com.example.notesapp.fragment.NoteListFragmentDirections

class NotesAdapter(val context: Context, private var noteList: List<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(val binding: NoteItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = noteList[position]
        val myHolder = holder.binding
        myHolder.noteNameTv.text = note.name
        myHolder.dateTv.text = note.date
        myHolder.root.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToDetailFragment(note)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = noteList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newNotesList: List<Note>) {
        noteList = newNotesList
        notifyDataSetChanged()
    }
}
