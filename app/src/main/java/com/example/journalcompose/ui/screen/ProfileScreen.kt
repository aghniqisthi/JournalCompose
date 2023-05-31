package com.example.journalcompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.journalcompose.R
import com.example.journalcompose.ui.theme.JournalComposeTheme

@Composable
fun ProfileScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.bg_profile),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column{
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateBack() }
                    .align(Alignment.Start)
            )
            AsyncImage(
                model = R.drawable.profile_photos,
                modifier = modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(26.dp),
                contentDescription = null
            )

            Text(stringResource(id = R.string.stu_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )
            Text(stringResource(id = R.string.name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )

            Text(stringResource(id = R.string.stu_email),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )
            Text(stringResource(id = R.string.email),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )

            Text(stringResource(id = R.string.stu_bangkit),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )
            Text(stringResource(id = R.string.bangkit_email),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    JournalComposeTheme {
        ProfileScreen(navigateBack = { })
    }
}