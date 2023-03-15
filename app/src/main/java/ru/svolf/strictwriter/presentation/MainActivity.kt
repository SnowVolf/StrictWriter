package ru.svolf.strictwriter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.svolf.core.theme.AppTheme
import ru.svolf.writter_feature.note.presentation.add_edit_note.AddEditNoteScreen
import ru.svolf.writter_feature.note.presentation.notes.NotesScreen
import ru.svolf.writter_feature.note.presentation.util.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AppTheme {
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
					val navController = rememberNavController()
					NavHost(
						navController = navController,
						startDestination = Screen.NotesScreen.route
					) {
						composable(route = Screen.NotesScreen.route) {
							NotesScreen(navController = navController)
						}
						composable(route = Screen.AddEditNotesScreen.route +
							"?noteId={noteId}&noteColor={noteColor}",
							arguments = listOf(
								navArgument("noteId") {
									type = NavType.IntType
									defaultValue = -1
								},
								navArgument("noteColor") {
									type = NavType.IntType
									defaultValue = -1
								}
							)
						) {
							val color = it.arguments?.getInt("noteColor") ?: -1
							AddEditNoteScreen(navController = navController, noteColor = color)
						}
					}
				}
			}
		}
	}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	AppTheme {

	}
}