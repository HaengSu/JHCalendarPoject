package com.android.jhcalendarpoject.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.jhcalendarpoject.compose.BoardPage


@Composable
fun NavigationGraph(navCon : NavHostController) {
    NavHost(navController = navCon, startDestination = BottomNavItem.Calendar.screenRoute) {
        composable(BottomNavItem.Calendar.screenRoute) {
            CalendarPage()
        }
        composable(BottomNavItem.Board.screenRoute) {
            BoardPage()
        }
        composable(BottomNavItem.Memo.screenRoute) {
            MemoPage()
        }
    }
}