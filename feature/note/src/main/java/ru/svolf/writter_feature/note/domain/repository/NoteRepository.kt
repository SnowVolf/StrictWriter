package ru.svolf.writter_feature.note.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.svolf.writter_feature.note.domain.model.Note

/*
 * Created by SVolf on 13.03.2023, 16:12
 * This file is a part of "StrictWriter" project
 */
interface NoteRepository {

	fun getNotes(): Flow<List<Note>>

	suspend fun getById(id: Int): Note?

	suspend fun insertNote(note: Note)

	suspend fun deleteNote(note: Note)

}