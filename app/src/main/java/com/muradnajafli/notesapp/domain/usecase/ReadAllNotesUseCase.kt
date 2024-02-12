package com.muradnajafli.notesapp.domain.usecase

import com.muradnajafli.notesapp.data.model.Note
import com.muradnajafli.notesapp.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReadAllNotesUseCaseImpl @Inject constructor(
    private val repository: NoteRepository
) : ReadAllNotesUseCase {
    override suspend operator fun invoke() =
        withContext(Dispatchers.IO) {
            repository.readAllData()
        }
}

interface ReadAllNotesUseCase {
    suspend operator fun invoke(): Flow<List<Note>>
}