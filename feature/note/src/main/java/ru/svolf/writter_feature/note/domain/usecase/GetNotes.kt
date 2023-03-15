package ru.svolf.writter_feature.note.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.repository.NoteRepository
import ru.svolf.writter_feature.note.domain.util.NoteOrder
import ru.svolf.writter_feature.note.domain.util.OrderType

/*
 * Created by SVolf on 13.03.2023, 16:19
 * This file is a part of "StrictWriter" project
 */
class GetNotes(
	private val repository: NoteRepository
) {

	operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> {
		return repository.getNotes().map { notes ->
			when (noteOrder.orderType) {
				is OrderType.Ascending -> {
					when (noteOrder) {
						is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
						is NoteOrder.Date -> notes.sortedBy { it.timestamp }
						is NoteOrder.Color -> notes.sortedBy { it.color }
					}
				}
				is OrderType.Descending -> {
					when (noteOrder) {
						is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
						is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
						is NoteOrder.Color -> notes.sortedByDescending { it.color }
					}
				}
			}
		}
	}

}