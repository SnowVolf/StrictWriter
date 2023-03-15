package ru.svolf.writter_feature.note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import ru.svolf.writter_feature.note.domain.model.Note

/*
 * Created by SVolf on 13.03.2023, 19:18
 * This file is a part of "StrictWriter" project
 */
@Composable
fun NoteItem(
	note: Note,
	modifier: Modifier = Modifier,
	cornerRadius: Dp = 10.dp,
	cutOutRadius: Dp = 30.dp,
	onDeleteClick: () -> Unit
) {

	Box(modifier = modifier) {
		Canvas(modifier = Modifier.matchParentSize()) {
			val clipPath = Path().apply {
				lineTo(size.width - cutOutRadius.toPx(), 0F)
				lineTo(size.width, cutOutRadius.toPx())
				lineTo(size.width, size.height)
				lineTo(0F, size.height)
				close()
			}
			clipPath(clipPath) {
				drawRoundRect(
					color = Color(note.color),
					size = size,
					cornerRadius = CornerRadius(cornerRadius.toPx())
				)
				drawRoundRect(
					color = Color(ColorUtils.blendARGB(note.color, 0x000000, 0.2F)),
					topLeft = Offset(size.width - cutOutRadius.toPx(), -100F),
					size = Size(cutOutRadius.toPx() + 100F, cutOutRadius.toPx() + 100F),
					cornerRadius = CornerRadius(cornerRadius.toPx())
				)
			}
		}
		Column(modifier = Modifier
			.fillMaxSize()
			.padding(16.dp)
			.padding(end = 32.dp)
		) {
			Text(
				text = note.title,
				style = MaterialTheme.typography.headlineSmall,
				color = MaterialTheme.colorScheme.onSurface,
				maxLines = 1,
				overflow = TextOverflow.Ellipsis
			)
			Spacer(modifier = Modifier.height(8.dp))
			Text(
				text = note.content,
				style = MaterialTheme.typography.bodyMedium,
				color = MaterialTheme.colorScheme.onSurface,
				maxLines = 10,
				overflow = TextOverflow.Ellipsis
			)
		}
		IconButton(
			onClick = onDeleteClick,
			Modifier.align(Alignment.BottomEnd)
		) {
			Icon(
				imageVector = Icons.TwoTone.Delete,
				contentDescription = "Delete note"
			)
		}
	}

}