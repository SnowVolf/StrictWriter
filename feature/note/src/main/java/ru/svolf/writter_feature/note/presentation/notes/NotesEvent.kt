package ru.svolf.writter_feature.note.presentation.notes

import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.util.NoteOrder


/*
 * Created by SVolf on 13.03.2023, 17:21
 * This file is a part of "StrictWriter" project
 */
sealed class NotesEvent {
 data class Order(val noteOrder: NoteOrder): NotesEvent()

 data class DeleteNote(val note: Note): NotesEvent()

 object RestoreNote: NotesEvent()

 object ToggleOrderSection: NotesEvent()

}
