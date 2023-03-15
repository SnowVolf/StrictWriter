package ru.svolf.strictwriter.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.svolf.writter_feature.note.data.datasource.NoteDatabase
import ru.svolf.writter_feature.note.data.repository.NoteRepositoryImpl
import ru.svolf.writter_feature.note.domain.repository.NoteRepository
import ru.svolf.writter_feature.note.domain.usecase.*
import javax.inject.Singleton

/*
 * Created by SVolf on 13.03.2023, 16:46
 * This file is a part of "StrictWriter" project
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	@Provides
	@Singleton
	fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
		return Room.databaseBuilder(context, NoteDatabase::class.java, NoteDatabase.NAME).build()
	}

	@Provides
	@Singleton
	fun provideNoteRepository(db: NoteDatabase): NoteRepository {
		return NoteRepositoryImpl(db.noteDao)
	}

	@Provides
	@Singleton
	fun provideNotesUseCase(repository: NoteRepository): NoteUseCases {
		return NoteUseCases(
			getNotes = GetNotes(repository),
			deleteNote = DeleteNote(repository),
			addNote = AddNote(repository),
			getNote = GetNote(repository)
		)
	}
}