package ru.svolf.writter_feature.note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.svolf.writter_feature.note.R
import ru.svolf.writter_feature.note.domain.util.NoteOrder
import ru.svolf.writter_feature.note.domain.util.OrderType

/*
 * Created by SVolf on 13.03.2023, 18:29
 * This file is a part of "StrictWriter" project
 */
@Composable
fun OrderSection(
	modifier: Modifier = Modifier,
	noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
	onOrderChange: (NoteOrder) -> Unit
) {
	Column(
		modifier = modifier
	) {
		Row(
			modifier = Modifier.fillMaxWidth()
		) {
			DefaultRadioButton(
				text = stringResource(R.string.sort_title),
				selected = noteOrder is NoteOrder.Title,
				onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
			)
			Spacer(modifier = Modifier.width(8.dp))
			DefaultRadioButton(
				text = stringResource(R.string.sort_date),
				selected = noteOrder is NoteOrder.Date,
				onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) }
			)
			Spacer(modifier = Modifier.width(8.dp))
			DefaultRadioButton(
				text = stringResource(R.string.sort_color),
				selected = noteOrder is NoteOrder.Color,
				onSelect = { onOrderChange(NoteOrder.Color(noteOrder.orderType)) }
			)
		}
		Spacer(modifier = Modifier.height(16.dp))
		Row(
			modifier = Modifier.fillMaxWidth()
		) {
			DefaultRadioButton(
				text = stringResource(R.string.sort_asc),
				selected = noteOrder.orderType is OrderType.Ascending,
				onSelect = {
					onOrderChange(noteOrder.copy(OrderType.Ascending))
				}
			)
			Spacer(modifier = Modifier.width(8.dp))
			DefaultRadioButton(
				text = stringResource(R.string.sort_desc),
				selected = noteOrder.orderType is OrderType.Descending,
				onSelect = {
					onOrderChange(noteOrder.copy(OrderType.Descending))
				}
			)
		}
	}
}