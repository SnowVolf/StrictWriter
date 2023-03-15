package ru.svolf.writter_feature.note.presentation.notes

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.svolf.writter_feature.note.R
import ru.svolf.writter_feature.note.presentation.notes.components.NoteItem
import ru.svolf.writter_feature.note.presentation.notes.components.OrderSection
import ru.svolf.writter_feature.note.presentation.util.Screen

/*
 * Created by SVolf on 14.03.2023, 18:10
 * This file is a part of "StrictWriter" project
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
	navController: NavController,
	viewModel: NotesViewModel = hiltViewModel()
) {
	val state = viewModel.state.value
	val snackbarHostState = remember { SnackbarHostState() }
	val scope = rememberCoroutineScope()

	Scaffold(
		snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
		floatingActionButton = {
			FloatingActionButton(
				onClick = {
					navController.navigate(Screen.AddEditNotesScreen.route)
				}
			) {
				Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add_note))
			}
		},
	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(8.dp)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(
					text = stringResource(R.string.notes_title),
					style = MaterialTheme.typography.headlineSmall
				)
				IconButton(
					onClick = {
						viewModel.onEvent(NotesEvent.ToggleOrderSection)
					},
				) {
					Icon(
						imageVector = Icons.Default.Sort,
						contentDescription = stringResource(R.string.notes_sort)
					)
				}
			}
			AnimatedVisibility(
				visible = state.isOrderSectionVisible,
				enter = fadeIn() + slideInVertically(),
				exit = fadeOut() + slideOutVertically()
			) {
				OrderSection(
					modifier = Modifier
						.fillMaxWidth()
						.padding(vertical = 8.dp),
					noteOrder = state.noteOrder,
					onOrderChange = {
						viewModel.onEvent(NotesEvent.Order(it))
					}
				)
			}
			Spacer(modifier = Modifier.height(16.dp))
			LazyColumn(modifier = Modifier.fillMaxSize()) {
				items(state.notes) { note ->
					NoteItem(
						note = note,
						modifier = Modifier
							.fillMaxWidth()
							.clickable {
								navController.navigate(
									Screen.AddEditNotesScreen.route +
										"?noteId=${note.id}&noteColor=${note.color}"
								)
							},
						onDeleteClick = {
							viewModel.onEvent(NotesEvent.DeleteNote(note))
							scope.launch {
								val result = snackbarHostState.showSnackbar(
									message = "Note deleted",
									actionLabel = "Undo"
								)
								if(result == SnackbarResult.ActionPerformed) {
									viewModel.onEvent(NotesEvent.RestoreNote)
								}
							}
						}
					)
					Spacer(modifier = Modifier.height(16.dp))
				}
			}
		}
	}
}