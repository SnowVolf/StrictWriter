package ru.svolf.writter_feature.note.domain.usecase

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThrows

import org.junit.Before
import org.junit.Test
import ru.svolf.writter_feature.note.data.repository.FakeNoteRepository
import ru.svolf.writter_feature.note.domain.model.InvalidNoteException
import ru.svolf.writter_feature.note.domain.model.Note

/*
 * Created by SVolf on 18.03.2023, 17:45
 * This file is a part of "StrictWriter" project
 */
class AddNoteTest {

	private lateinit var addNote: AddNote
	private lateinit var fakeNoteRepository: FakeNoteRepository
	private val notesToInsert = mutableListOf<Note>()

	@Before
	fun setUp() {
		fakeNoteRepository = FakeNoteRepository()
		addNote = AddNote(fakeNoteRepository)
		('a'..'b').forEachIndexed { index, c ->
			notesToInsert.add(
				Note(
					title = if (index % 2 == 1) c.toString() else "",
					content = if (index % 2 == 0) c.toString() else "",
					timestamp = c.code.toLong(),
					color = index
				)
			)
		}
	}


	@Test
	fun `Check for exception empty title`() {
		assertThrows(InvalidNoteException::class.java) {
			runBlocking {
				if (notesToInsert[1].content.isEmpty())
					addNote.invoke(notesToInsert[1])
			}
		}
	}

	@Test
	fun `Check for exception empty content`() {
		assertThrows(InvalidNoteException::class.java) {
			runBlocking {
				if (notesToInsert[0].title.isEmpty())
					addNote.invoke(notesToInsert[0])
			}
		}
	}

}