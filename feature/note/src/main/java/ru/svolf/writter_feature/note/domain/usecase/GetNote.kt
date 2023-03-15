package ru.svolf.writter_feature.note.domain.usecase

import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.repository.NoteRepository


/*
 * Created by SVolf on 14.03.2023, 19:39
 * This file is a part of "StrictWriter" project
 */
class GetNote(
	private val repository: NoteRepository
) {

	suspend operator fun invoke(id: Int): Note? {
		return repository.getById(id)
	}

}