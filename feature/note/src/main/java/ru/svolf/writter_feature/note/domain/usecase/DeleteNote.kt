package ru.svolf.writter_feature.note.domain.usecase

import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.repository.NoteRepository


/*
 * Created by SVolf on 13.03.2023, 16:39
 * This file is a part of "StrictWriter" project
 */
class DeleteNote(
	private val repository: NoteRepository
) {

	suspend operator fun invoke(note: Note) {
		repository.deleteNote(note)
	}

}