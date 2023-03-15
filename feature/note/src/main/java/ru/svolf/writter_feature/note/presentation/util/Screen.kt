package ru.svolf.writter_feature.note.presentation.util

/*
 * Created by SVolf on 14.03.2023, 22:33
 * This file is a part of "StrictWriter" project
 */
sealed class Screen(val route: String) {
	object NotesScreen: Screen("notes_screen")
	object AddEditNotesScreen: Screen("add_edit_note")

}
