package com.muradnajafli.notesapp.data.di

import com.muradnajafli.notesapp.data.repository.NoteRepository
import com.muradnajafli.notesapp.domain.AddNoteUseCase
import com.muradnajafli.notesapp.domain.AddNoteUseCaseImpl
import com.muradnajafli.notesapp.domain.DeleteNoteUseCase
import com.muradnajafli.notesapp.domain.DeleteNoteUseCaseImpl
import com.muradnajafli.notesapp.domain.ReadAllNotesUseCase
import com.muradnajafli.notesapp.domain.ReadAllNotesUseCaseImpl
import com.muradnajafli.notesapp.domain.UpdateNoteUseCase
import com.muradnajafli.notesapp.domain.UpdateNoteUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideAddNoteUseCase(
        repository: NoteRepository
    ): AddNoteUseCase {
        return AddNoteUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideReadAllNotesUseCase(
        repository: NoteRepository
    ): ReadAllNotesUseCase {
        return ReadAllNotesUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideDeleteNoteUseCase(
        repository: NoteRepository
    ): DeleteNoteUseCase {
        return DeleteNoteUseCaseImpl(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideUpdateNoteUseCase(
        repository: NoteRepository
    ): UpdateNoteUseCase {
        return UpdateNoteUseCaseImpl(repository)
    }

}