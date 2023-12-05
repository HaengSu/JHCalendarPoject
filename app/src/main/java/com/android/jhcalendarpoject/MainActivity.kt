package com.android.jhcalendarpoject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.jhcalendarpoject.compose.BottomNavItem
import com.android.jhcalendarpoject.ui.theme.JHCalendarPojectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JHCalendarPojectTheme {
                // A surface container using the 'background' color from the theme
                BottomNavigation()
            }
        }
    }
}

@Composable
fun BottomNavigation() {
    val bottomItems = listOf(
        BottomNavItem.Calendar,
        BottomNavItem.Board,
        BottomNavItem.Memo
    )

    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        contentColor = colorResource(id = R.color.main_color),
    ) {
        bottomItems.forEach {item ->
            BottomNavigationItem(
                icon = {},
                label = { Text(text = item.title) },
                selected = true,
                onClick = {
                },
                alwaysShowLabel = false,
                selectedContentColor = colorResource(id = R.color.main_color),
                unselectedContentColor = colorResource(id = R.color.main_color),
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