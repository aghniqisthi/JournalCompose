package com.example.journalcompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.journalcompose.R
import com.example.journalcompose.ui.theme.JournalComposeTheme

@Preview(showBackground = true)
@Composable
fun DetailItemPreview() {
    JournalComposeTheme {
        DetailItem(
            title = "Title Journal Information Systems",
            volume = "Volume 10",
            desc = "This is journal description that explains a lot what is the journal talking about.",
            link = "www.google.com",
            image = R.drawable.information_systems,
            statusFav = false,
            onBackClick = { /*TODO*/ },
            onClickToFav = { /*TODO*/ }
        )
    }
}

@Composable
fun DetailItem(
    title: String,
    volume: String,
    desc: String,
    link: String,
    @DrawableRes image: Int,
    statusFav: Boolean,
    onBackClick: () -> Unit,
    onClickToFav: (status: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    val status by rememberSaveable { mutableStateOf(statusFav) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.back),
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBackClick() }
                .align(Alignment.Start)
        )
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = volume,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5.copy(
                fontWeight = FontWeight.SemiBold
            ),
        )
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = modifier
                .padding(14.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Text(
            text = desc,
            style = MaterialTheme.typography.body2,
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,
        )
        Text(
            text = link,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            fontSize = 18.sp,
            color = MaterialTheme.colors.primary
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color.LightGray)
        )
        FavButton(
            status = status,
            enabled = true,
            onClick = {
                onClickToFav(status)
            }
        )
    }
}
