package com.muradnajafli.notesapp.data.repository

import com.muradnajafli.notesapp.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun readAllData(): Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}