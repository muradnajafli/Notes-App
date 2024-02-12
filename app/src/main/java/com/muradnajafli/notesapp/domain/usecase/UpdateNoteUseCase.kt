package com.muradnajafli.notesapp.domain.usecase

import com.muradnajafli.notesapp.data.model.Note
import com.muradnajafli.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateNoteUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : UpdateNoteUseCase {

    override suspend operator fun invoke(note: Note) =
        withContext(Dispatchers.IO) {
            repository.updateNote(note)
        }
}

interface UpdateNoteUseCase {
    suspend operator fun invoke(note: Note)
}

