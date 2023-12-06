package com.android.jhcalendarpoject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.jhcalendarpoject.compose.BottomNavItem
import com.android.jhcalendarpoject.compose.NavigationGraph
import com.android.jhcalendarpoject.ui.theme.JHCalendarPojectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JHCalendarPojectTheme {
                MainScreenView()
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Box(Modifier.padding(it)) {
            NavigationGraph(navCon = navController)
        }
    }
}

@Composable
fun BottomNavigation(navController: NavHostController) {
    val bottomItems = listOf(
        BottomNavItem.Calendar,
        BottomNavItem.Board,
        BottomNavItem.Memo
    )

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        contentColor = colorResource(id = R.color.main_color),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomItems.forEach { item ->
            BottomNavigationItem(
                icon = {},
                label = { Text(text = item.title) },
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                    }
                },
                alwaysShowLabel = false,
                selectedContentColor = colorResource(id = R.color.main_color),
                unselectedContentColor = colorResource(id = R.color.black),
                modifier = Modifier.background(colorResource(id = R.color.white))
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JHCalendarPojectTheme {
        Greeting("Android")
    }
}