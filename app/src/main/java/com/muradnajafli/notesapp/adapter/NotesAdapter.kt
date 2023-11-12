package com.muradnajafli.notesapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.analytics.FirebaseAnalytics
import com.muradnajafli.notesapp.data.Note
import com.muradnajafli.notesapp.databinding.NoteItemBinding
import com.muradnajafli.notesapp.fragment.NoteListFragmentDirections

class NotesAdapter(val context: Context, private var noteList: List<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

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

            logFirebaseEvent()
        }
    }

    override fun getItemCount() = noteList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newNotesList: List<Note>) {
        noteList = newNotesList
        notifyDataSetChanged()
    }

    private fun logFirebaseEvent() {
        val bundle = Bundle().apply {
            putInt("view_note_content", 1)
        }
        firebaseAnalytics.logEvent("view_note", bundle)
    }
}
