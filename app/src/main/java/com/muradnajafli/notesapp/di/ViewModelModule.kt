package com.muradnajafli.notesapp.di

import com.muradnajafli.notesapp.domain.repository.NoteRepository
import com.muradnajafli.notesapp.domain.usecase.AddNoteUseCase
import com.muradnajafli.notesapp.domain.usecase.AddNoteUseCaseImpl
import com.muradnajafli.notesapp.domain.usecase.DeleteNoteUseCase
import com.muradnajafli.notesapp.domain.usecase.DeleteNoteUseCaseImpl
import com.muradnajafli.notesapp.domain.usecase.ReadAllNotesUseCase
import com.muradnajafli.notesapp.domain.usecase.ReadAllNotesUseCaseImpl
import com.muradnajafli.notesapp.domain.usecase.UpdateNoteUseCase
import com.muradnajafli.notesapp.domain.usecase.UpdateNoteUseCaseImpl
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