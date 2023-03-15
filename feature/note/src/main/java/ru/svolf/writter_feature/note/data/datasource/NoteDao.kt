package ru.svolf.writter_feature.note.data.datasource

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.svolf.writter_feature.note.domain.model.Note

/*
 * Created by SVolf on 13.03.2023, 15:51
 * This file is a part of "StrictWriter" project
 */
@Dao
interface NoteDao {
	@Query("SELECT * FROM note")
	fun getNotes(): Flow<List<Note>>

	@Query("SELECT * FROM note WHERE id =:id")
	suspend fun getById(id: Int): Note?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertNote(note: Note)

	@Delete
	suspend fun deleteNote(note: Note)
}