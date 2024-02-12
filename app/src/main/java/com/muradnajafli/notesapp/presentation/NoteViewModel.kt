package com.muradnajafli.notesapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muradnajafli.notesapp.R
import com.muradnajafli.notesapp.data.model.Note
import com.muradnajafli.notesapp.presentation.util.UiText
import com.muradnajafli.notesapp.domain.usecase.AddNoteUseCase
import com.muradnajafli.notesapp.domain.usecase.DeleteNoteUseCase
import com.muradnajafli.notesapp.domain.usecase.ReadAllNotesUseCase
import com.muradnajafli.notesapp.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val readAllDataUseCase: ReadAllNotesUseCase
) : ViewModel() {

    private val _noteList: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList.asStateFlow()
    val errorMessage = MutableLiveData<UiText?>()

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            readAllDataUseCase()
                .catch {error ->
                    errorMessage.postValue(UiText.DynamicString(error.message))
                }
                .collect { notes ->
                    if (notes.isEmpty()) {
                        errorMessage.postValue(
                            UiText.StringResource(
                                R.string.you_dont_have_any_notes_yet
                            )
                        )
                    } else {
                        errorMessage.postValue(null)
                    }
                    _noteList.value = notes
                }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase(note)
        }
    }
}