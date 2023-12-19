package com.android.jhcalendarpoject.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.jhcalendarpoject.R

@Composable
fun CalendarPage() {
    Column {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ){
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

            Row (verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.image_love),
                    contentDescription = "ddayIcon",
                    modifier = Modifier
                        .size(20.dp)
                )
                Text(text = "36")
            }
        }




    }
}

@Preview
@Composable
fun PreviewCalendarPage() {
    CalendarPage()
}