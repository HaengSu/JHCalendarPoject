package com.android.jhcalendarpoject.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.jhcalendarpoject.R

@Composable
fun CalendarPage() {
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
}