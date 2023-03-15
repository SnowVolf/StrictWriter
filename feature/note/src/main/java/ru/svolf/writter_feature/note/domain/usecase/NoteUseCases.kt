package ru.svolf.writter_feature.note.domain.usecase

/*
 * Created by SVolf on 13.03.2023, 16:42
 * This file is a part of "StrictWriter" project
 */
data class NoteUseCases(val getNotes: GetNotes, val deleteNote: DeleteNote, val addNote: AddNote, val getNote: GetNote) {
}