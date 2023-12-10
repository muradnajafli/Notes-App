package com.muradnajafli.notesapp.data.repository

import com.muradnajafli.notesapp.data.model.Note
import com.muradnajafli.notesapp.data.room.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override suspend fun readAllData(): Flow<List<Note>> {
        return noteDao.readAllData()
    }

    override suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}