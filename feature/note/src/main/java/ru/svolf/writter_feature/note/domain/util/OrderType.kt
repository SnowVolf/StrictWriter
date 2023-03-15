package ru.svolf.writter_feature.note.domain.util

/*
 * Created by SVolf on 13.03.2023, 16:23
 * This file is a part of "StrictWriter" project
 */
sealed class OrderType {
	object Ascending : OrderType()
	object Descending : OrderType()
}
