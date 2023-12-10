package com.muradnajafli.notesapp.domain

import com.muradnajafli.notesapp.data.model.Note
import com.muradnajafli.notesapp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : DeleteNoteUseCase {

    override suspend operator fun invoke(note: Note) =
        withContext(Dispatchers.IO) {
            repository.deleteNote(note)
        }
}

interface DeleteNoteUseCase{
    suspend operator fun invoke(note: Note)
}