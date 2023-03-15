package ru.svolf.writter_feature.note.domain.usecase

import ru.svolf.writter_feature.note.domain.model.InvalidNoteException
import ru.svolf.writter_feature.note.domain.model.Note
import ru.svolf.writter_feature.note.domain.repository.NoteRepository


/*
 * Created by SVolf on 13.03.2023, 17:47
 * This file is a part of "StrictWriter" project
 */
class AddNote(private val repository: NoteRepository) {

 @Throws(InvalidNoteException::class)
 suspend operator fun invoke(note: Note) {
  if (note.title.isBlank() or note.content.isBlank()) {
   throw  InvalidNoteException("Content or title is blank!")
  }
  repository.insertNote(note)
 }

}