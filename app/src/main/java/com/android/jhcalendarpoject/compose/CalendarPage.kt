package com.android.jhcalendarpoject.compose

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.jhcalendarpoject.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarPage() {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .padding(start = 21.dp, top = 7.dp)
                    .size(64.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .background(color = androidx.compose.ui.graphics.Color.LightGray)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.image_love),
                    contentDescription = "ddayIcon",
                    modifier = Modifier
                        .size(20.dp)
                )
                Text(text = "36")
            }
        }

        CustomCalendarHeader()
    }
}


@Composable
fun CustomCalendarHeader() {
    val year = LocalDate.now().year
    val month = LocalDate.now().monthValue
    val formatter = SimpleDateFormat("MMM", Locale.US)
    val monthName = formatter.format(month)

    val day = LocalDate.now().dayOfMonth


    Column {
        // 년월 표시 라인
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 19.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_arrow_left),
                contentDescription = "arrowLeft"
            )

            Text(text = "${year} ${monthName}")

            Image(
                painter = painterResource(id = R.drawable.image_arrow_right),
                contentDescription = "arrowRight"
            )

        }

        //요일 표시 라인
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Text(text = "S", modifier = Modifier.padding(start = 38.dp))
            Text(text = "M")
            Text(text = "T")
            Text(text = "W")
            Text(text = "T")
            Text(text = "F")
            Text(text = "S", modifier = Modifier.padding(end = 38.dp))
        }

        //요일 하단 라인
        Row(
            modifier = Modifier
                .padding(top = 12.dp, start = 21.dp, end = 21.dp)
                .fillMaxWidth()
                .height(2.dp)
                .background(color = androidx.compose.ui.graphics.Color.Black)
        ) {
        }

        //날짜 표시 라인
        CustomCalendarBody()
        //https://velog.io/@abh0920one/Compose-Custom-Calendar-%EC%A0%9C%EC%9E%91%ED%95%98%EA%B8%B0 참고
    }
}

/**
 * 캘린더 날짜 표시
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCalendarBody(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit
) {
    val lastDay by remember { mutableStateOf(currentDate.lengthOfMonth()) }
    val firstDayOfWeek by remember { mutableStateOf(currentDate.dayOfWeek.value) }
    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }
    Column {
        LazyVerticalGrid(
            modifier = Modifier.height(260.dp),
            columns = GridCells.Fixed(7)
        ) {
            for (i in 1 until firstDayOfWeek) {
                item {
                    Box (
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp)

                    )
                }
            }

            items(days) { day ->
                val date = currentDate.withDayOfMonth(day)
                val isSelected = remember(selectedDate) {
                    selectedDate.compareTo(date) == 0
                }
                CalendarDay(
                    modifier = Modifier.padding(top = 10.dp),
                    date = date,
                    isToday = date == LocalDate.now(),
                    isSelected = isSelected,
                    onSelectedDate = onSelectedDate
                )
            }
        }
    }
}

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isToday: Boolean,
    isSelected: Boolean,
    onSelectedDate: (LocalDate) -> Unit
) {
//    val hasEvent = false // TODO
//    Column(
//        modifier = modifier
//            .wrapContentSize()
//            .size(30.dp)
//            .clip(shape = RoundedCornerShape(10.dp))
//            .conditional(isToday) {
//                background(gray07)
//            }
//            .conditional(isSelected) {
//                background(gray0)
//            }
//            .conditional(!isToday && !isSelected) {
//                background(gray08)
//            }
//            .noRippleClickable { onSelectedDate(date) },
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//
//    ) {
//        val textColor = if (isSelected) gray09 else gray0
//        Text(
//            modifier = Modifier,
//            textAlign = TextAlign.Center,
//            text = date.dayOfMonth.toString(),
//            style = BoldN12,
//            color = textColor
//        )
//        if (hasEvent) {
//            Box(
//                modifier = Modifier
//                    .size(4.dp)
//                    .clip(shape = RoundedCornerShape(4.dp))
//                    .conditional(isSelected) {
//                        background(gray09)
//                    }
//                    .conditional(!isSelected) {
//                        background(gray0)
//                    }
//            )
//        }
//    }
}

@Preview
@Composable
fun PreviewCalendarPage() {
    CalendarPage()
}