package ru.svolf.writter_feature.note.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.svolf.writter_feature.note.domain.model.Note

/*
 * Created by SVolf on 13.03.2023, 15:57
 * This file is a part of "StrictWriter" project
 */
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
	abstract val noteDao: NoteDao

	companion object {
		const val NAME = "notes"
	}

}