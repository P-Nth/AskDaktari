package com.example.askdaktari.ui.theme.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TopNavBar(navBarName: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TNavComponents(navBarName = navBarName)
    }
}

@Composable
fun TNavComponents(navBarName: String) {
    TopAppBar(
        modifier = Modifier,
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
                }
                Text(
                    modifier = Modifier.fillMaxWidth(.7f),
                    text = navBarName,
                    style = TextStyle(
                        fontSize = 25.sp,
                        letterSpacing = 1.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    )
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile Icon",
                        modifier = Modifier.width(20.dp)
                    )
                }
            }

    }
}