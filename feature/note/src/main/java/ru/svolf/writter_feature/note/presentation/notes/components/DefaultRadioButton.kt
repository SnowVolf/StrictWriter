package ru.svolf.writter_feature.note.presentation.notes.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
 * Created by SVolf on 13.03.2023, 18:20
 * This file is a part of "StrictWriter" project
 */
@Composable
fun DefaultRadioButton(
	text: String,
	selected: Boolean,
	onSelect: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		RadioButton(
			selected = selected,
			onClick = onSelect,
			colors = RadioButtonDefaults.colors(
				selectedColor = MaterialTheme.colorScheme.primary,
				unselectedColor = MaterialTheme.colorScheme.onBackground
			)
		)
		Spacer(modifier = Modifier.width(8.dp))
		Text(text = text, style = MaterialTheme.typography.bodyMedium)
	}
}