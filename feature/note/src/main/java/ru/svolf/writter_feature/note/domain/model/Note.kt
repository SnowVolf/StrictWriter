package ru.svolf.writter_feature.note.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.svolf.core.theme.*


/*
 * Created by SVolf on 13.03.2023, 15:32
 * This file is a part of "StrictWriter" project
 */
@Entity
data class Note(
	@ColumnInfo("title") val title: String,
	@ColumnInfo("content") val content: String,
	@ColumnInfo("timestamp") val timestamp: Long,
	@ColumnInfo("color") val color: Int,
	@PrimaryKey @ColumnInfo("id") val id: Int? = null
) {
	companion object {
		val noteColors = listOf(
			noteRed, notePurple, noteLightBlue, noteTeal, noteGreen,
			noteYellow, noteAmber
		)
	}
}
