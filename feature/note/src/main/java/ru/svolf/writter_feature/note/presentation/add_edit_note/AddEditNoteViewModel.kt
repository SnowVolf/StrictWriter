package ru.svolf.writter_feature.note.presentation.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.svolf.writter_feature.note.domain.model.InvalidNoteException
import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.usecase.NoteUseCases
import javax.inject.Inject

/*
 * Created by SVolf on 14.03.2023, 19:42
 * This file is a part of "StrictWriter" project
 */
@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
	private val noteUseCases: NoteUseCases,
	savedStateHandle: SavedStateHandle
): ViewModel() {
	private val _noteTitle = mutableStateOf(NoteTextFieldState(
		hint = "Enter a note title..."
	))
	val noteTitle: State<NoteTextFieldState> = _noteTitle

	private val _noteContent = mutableStateOf(NoteTextFieldState(
		hint = "Enter some content"
	))
	val noteContent: State<NoteTextFieldState> = _noteContent

	private val _noteColor = mutableStateOf(Note.noteColors.random().toArgb())
	val noteColor: State<Int> = _noteColor

	private val _eventFlow = MutableSharedFlow<UiEvent>()
	val eventFlow = _eventFlow.asSharedFlow()

	private var currentNoteId: Int? = null

	init {
		savedStateHandle.get<Int>("noteId")?.let { noteId ->
			if (noteId != -1) {
				viewModelScope.launch {
					noteUseCases.getNote(noteId)?.let {
						currentNoteId = it.id
						_noteTitle.value = noteTitle.value.copy(
							text = it.title,
							isHintVisible = false
						)
						_noteContent.value = noteContent.value.copy(
							text = it.content,
							isHintVisible = false
						)
					}
				}
			}
		}
	}

	fun onEvent(event: AddEditNoteEvent) {
		when(event) {
			is AddEditNoteEvent.EnteredTitle -> {
				_noteTitle.value = noteTitle.value.copy(text = event.value)
			}
			is AddEditNoteEvent.ChangeTitleFocus -> {
				_noteTitle.value = noteTitle.value.copy(
					isHintVisible = !event.focus.isFocused && noteTitle.value.text.isBlank()
				)
			}
			is AddEditNoteEvent.EnteredContent -> {
				_noteContent.value = noteContent.value.copy(text = event.value)
			}
			is AddEditNoteEvent.ChangeContentFocus -> {
				_noteContent.value = noteContent.value.copy(
					isHintVisible = !event.focus.isFocused && noteContent.value.text.isBlank()
				)
			}
			is AddEditNoteEvent.ChangeColor -> {
				_noteColor.value = event.color
			}
			is AddEditNoteEvent.SaveNote -> {
				viewModelScope.launch {
					try {
						noteUseCases.addNote(
							Note(
								title = noteTitle.value.text,
								content = noteContent.value.text,
								color = noteColor.value,
								timestamp = System.currentTimeMillis(),
								id = currentNoteId
							)
						)
						_eventFlow.emit(UiEvent.SaveNote)
					} catch (e: InvalidNoteException) {
						_eventFlow.emit(
							UiEvent.ShowSnackbar(e.message ?: "Could not save note")
						)
					}
				}
			}
		}
	}


	sealed class UiEvent {
		data class ShowSnackbar(val message: String): UiEvent()
		object SaveNote: UiEvent()
	}
}