package ru.svolf.writter_feature.note.presentation.add_edit_note

/*
 * Created by SVolf on 14.03.2023, 19:51
 * This file is a part of "StrictWriter" project
 */
data class NoteTextFieldState(
 val text: String = "", val hint: String = "", val isHintVisible: Boolean = true
)