package ru.svolf.writter_feature.note.data.repository

import kotlinx.coroutines.flow.Flow
import ru.svolf.writter_feature.note.data.datasource.NoteDao
import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.repository.NoteRepository

/*
 * Created by SVolf on 13.03.2023, 16:15
 * This file is a part of "StrictWriter" project
 */
class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
	override fun getNotes(): Flow<List<Note>> {
		return dao.getNotes()
	}

	override suspend fun getById(id: Int): Note? {
		return dao.getById(id)
	}

	override suspend fun insertNote(note: Note) {
		return dao.insertNote(note)
	}

	override suspend fun deleteNote(note: Note) {
		return dao.deleteNote(note)
	}
}