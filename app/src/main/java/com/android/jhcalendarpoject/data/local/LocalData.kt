package com.android.jhcalendarpoject.compose

import androidx.compose.ui.platform.LocalContext
const val CALENDAR = "CALENDAR"
const val BOARD = "BOARD"
const val MEMO = "MEMO"

sealed class BottomNavItem(
    val title: String, val screenRoute: String
) {
    object Calendar : BottomNavItem("Calendar", CALENDAR)
    object Board : BottomNavItem("Photo", BOARD)
    object Memo : BottomNavItem("Memo", MEMO)
}