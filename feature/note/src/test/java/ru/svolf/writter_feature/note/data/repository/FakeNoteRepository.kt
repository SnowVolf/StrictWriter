package ru.svolf.writter_feature.note.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.repository.NoteRepository

/*
 * Created by SVolf on 18.03.2023, 16:38
 * This file is a part of "StrictWriter" project
 */
class FakeNoteRepository : NoteRepository {

	private val notes = mutableListOf<Note>()


	override fun getNotes(): Flow<List<Note>> {
		return flow { emit(notes) }
	}

	override suspend fun getById(id: Int): Note? {
		return notes.find { it.id == id }
	}

	override suspend fun insertNote(note: Note) {
		notes.add(note)
	}

	override suspend fun deleteNote(note: Note) {
		notes.remove(note)
	}

}