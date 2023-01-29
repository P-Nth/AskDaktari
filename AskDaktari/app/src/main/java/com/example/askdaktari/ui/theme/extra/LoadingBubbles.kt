package com.example.askdaktari.ui.theme.extra

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.delay
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.CircleShape
import com.example.askdaktari.ui.theme.Blue500

@Composable
fun LoadingBubbles(
    modifier: Modifier = Modifier.fillMaxWidth(),
    bgColor: Color = Blue500,
    bubbleSize: Dp = 15.dp,
    amplitude: Dp = 20.dp

) {
    val bubbles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    bubbles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }
    
    val length = with(LocalDensity.current) {amplitude.toPx()}
    val bubbleValue = bubbles.map { it.value }
    val lastBubble = bubbleValue.size -1

    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        bubbleValue.forEachIndexed { index, value ->
            Box(
                modifier = Modifier
                    .size(bubbleSize)
                    .graphicsLayer {
                        translationY = -value * length
                    }
                    .background(bgColor, shape = CircleShape)
            ) {

            }
            if (index != lastBubble) Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    LoadingBubbles()
}