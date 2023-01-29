package com.example.askdaktari.ui.theme.media

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.askdaktari.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.painter.Painter
import com.example.askdaktari.ui.theme.Purple200

@Composable
fun FolderScreen() {
    Box(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FolderBox()
        }
    }
}

//@OptIn(ExperimentalFoundationApi::class)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FolderBox(
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "This are the available folders",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                fontSize = 22.sp,
            )
        )
        Spacer(modifier = Modifier.padding(20.dp))
        LazyVerticalGrid(
            columns  = GridCells.Adaptive(150.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            content = {
                item {
                    FolderItem(
                        title = "Documents",
                        bgColor = Purple200,
                        textColor = textColor,
                        imagePainter = painterResource(R.drawable.add_doc_icon)
                    )
                }
                item {
                    FolderItem(
                        title = "Images ",
                        bgColor = Purple200,
                        textColor = textColor,
                        imagePainter = painterResource(R.drawable.add_doc_icon)
                    )
                }
                item {
                    FolderItem(
                        title = "Videos",
                        bgColor = Purple200,
                        textColor = textColor,
                        imagePainter = painterResource(R.drawable.add_doc_icon)
                    )
                }
            }
        )
    }
}

@Composable
fun FolderItem(
    textColor: Color,
    title: String = "",
    imagePainter: Painter,
    bgColor: Color = Color.Transparent
    ) {
    Card(
        Modifier.height(130.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = bgColor,
        elevation = .5.dp
    ) {
        Row {
            Column (
                Modifier
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = imagePainter, contentDescription = "folder icon type",
                    modifier = Modifier
                        .fillMaxWidth(.3f)
                        .weight(1f),
                    alignment = Alignment.Center,

                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    color = textColor,
                    style = TextStyle(
                        fontSize = 18.sp,
                        letterSpacing = 1.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    FolderScreen()
}