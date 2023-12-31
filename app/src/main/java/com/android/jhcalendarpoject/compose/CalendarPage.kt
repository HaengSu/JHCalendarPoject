package com.android.jhcalendarpoject.compose

import android.os.Build
import android.util.Log
import android.widget.ImageButton
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.jhcalendarpoject.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.Duration.Companion.days

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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCalendarHeader() {
    var year by rememberSaveable {
        mutableStateOf(LocalDate.now().year)
    }
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val formatter = DateTimeFormatter.ofPattern("MMM", Locale.US)
    var monthName by remember { mutableStateOf(formatter.format(LocalDate.now())) }
    val day = LocalDate.now().dayOfMonth

    LaunchedEffect(currentMonth) {
        Log.i("##INFO", "month = ${currentMonth}")
    }


    Column {
        // 년월 표시 라인
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 19.dp)
        ) {
            IconButton(
                onClick = {
                    val beforeMonth = currentMonth.minusMonths(1)
                    currentMonth = beforeMonth
                    year = beforeMonth.year
                    monthName = formatter.format(beforeMonth)
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.image_arrow_left),
                    contentDescription = "arrowLeft"
                )
            }

            Text(text = "${year} ${monthName}")

            IconButton(
                onClick = {
                    val nextMonth = currentMonth.plusMonths(1)
                    currentMonth = nextMonth
                    year = nextMonth.year
                    monthName = formatter.format(nextMonth)
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.image_arrow_right),
                    contentDescription = "arrowLeft"
                )
            }

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

        //날짜 표시 라인2
        CustomCalendarBody(
            modifier = Modifier.fillMaxWidth(),
            currentDate = currentMonth.atDay(1),
            selectedDate = LocalDate.now(),
            onSelectedDate = {
            }
        )
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
    val lastDay = currentDate.month.length(currentDate.isLeapYear)

    // 일요일이 1부터 사작할 수 있게 +1, %7 적용  토요일은 0이 되므로 if문 처리
    var firstDayOfMonth = (currentDate.withDayOfMonth(1).dayOfWeek.value + 1) % 7
    if (firstDayOfMonth == 0) {
        firstDayOfMonth = 7
    }
    val firstDayOfWeek = firstDayOfMonth
    val days  = IntRange(1, lastDay).toList()

    Column(modifier = modifier) {
        LazyVerticalGrid(
            modifier = Modifier.height(260.dp),
            columns = GridCells.Fixed(7)
        ) {

            // TODO: 1/09 0부터 시작해서 한칸씩 밀렸음  수정 필요함 
            // 처음 날짜가 시작하는 요일 전까지 전 달의 날짜로 표시
            for (i in 0 until firstDayOfWeek) {
                val beforeMonth = currentDate.minusMonths(1)
                val lastDate = beforeMonth.month.length(beforeMonth.isLeapYear) - (firstDayOfMonth - i)
                item {
                    Column(
                        modifier = modifier
                            .padding(top = 10.dp)
                            .wrapContentSize()
                            .size(30.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            text = lastDate.toString(),
                            color = colorResource(id = R.color.gray_200 )
                        )
                    }
                }
            }
            Log.i("##INFO", "day = ${days}");

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

            // 마지막 날짜가 끝나고 나머지 날짜는 다음 달의 날짜로 표시
            // 1. 마지막주의 날짜가 7일이 아닐 경우
//            var lastDayOfWeek = currentDate.withDayOfMonth(lastDay).dayOfWeek.value +1 % 7
//            if (lastDayOfWeek == 0) {
//                lastDayOfWeek = 7
//            }
//            Log.i("##INFO", "lastDayOfWeek = ${lastDayOfWeek}")

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isToday: Boolean,
    isSelected: Boolean,
    onSelectedDate: (LocalDate) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .size(30.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            color = colorResource(id = R.color.black )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewCalendarPage() {
    Row(modifier = Modifier.background(color = colorResource(id = R.color.white))) {
        CalendarPage()
    }
}