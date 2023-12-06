package com.android.jhcalendarpoject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.jhcalendarpoject.data.local.BottomNavItem
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
                alwaysShowLabel = true,
                selectedContentColor = colorResource(id = R.color.main_color),
                unselectedContentColor = colorResource(id = R.color.black)
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