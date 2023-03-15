package ru.svolf.writter_feature.note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

/*
 * Created by SVolf on 14.03.2023, 20:10
 * This file is a part of "StrictWriter" project
 */
sealed class AddEditNoteEvent {
 data class EnteredTitle(val value: String): AddEditNoteEvent()
 data class ChangeTitleFocus(val focus: FocusState): AddEditNoteEvent()
 data class EnteredContent(val value: String): AddEditNoteEvent()
 data class ChangeContentFocus(val focus: FocusState): AddEditNoteEvent()
 data class ChangeColor(val color: Int): AddEditNoteEvent()
 object SaveNote: AddEditNoteEvent()

}