package ru.svolf.writter_feature.note.presentation.notes

import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.util.NoteOrder
import ru.svolf.writter_feature.note.domain.util.OrderType

/*
 * Created by SVolf on 13.03.2023, 17:15
 * This file is a part of "StrictWriter" project
 */
data class NotesState(
 val notes: List<Note> = emptyList(),
 val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
 val isOrderSectionVisible: Boolean = false
)