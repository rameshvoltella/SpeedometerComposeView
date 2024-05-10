package com.ramzmania.speedometerview


import android.graphics.Typeface
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.ramzmania.speedometercomposeview.Mode
import com.ramzmania.speedometercomposeview.SpeedometerComposeView

@Composable
fun SpeedometerUiViewCompose() {


    var targetValue by remember { mutableStateOf(0f) }
    var currentSpeed = remember(targetValue) { Animatable(initialValue = 220f) }
    var glowPoints by remember {
        mutableStateOf(false)
    }
//    var speedTypeFace by remember{ }
    val context = LocalContext.current
    val speedometerNumberFont = ResourcesCompat.getFont(context, R.font.font_speed_digits)
    val speedNumberFont = ResourcesCompat.getFont(context, R.font.font_speed)
    val typeFaceNUll: Typeface? = null

    var currentSpeedTypeFace by remember {
        mutableStateOf(typeFaceNUll)
    }
    var currentSpeedometerNumberTypeFace by remember {
        mutableStateOf(typeFaceNUll)
    }
    var speedoMeterMode by remember {
        mutableStateOf(Mode.NORMAL)
    }





    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(1.1f)
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = colorResource(id = R.color.black))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .wrapContentSize(Alignment.Center)

            ) {
                Column {
                    SpeedometerComposeView(
                        speedMeterMaxRange = 220,
                        currentSpeedValue = currentSpeed.value.toInt(),
                        needleColor = Color.Red,
                        speedTextColor = colorResource(
                            id = R.color.white,
                        ),
                        movingSpeedTextColor = Color.White,
                        arcWidth = 50f,
                        speedometerMode = speedoMeterMode,
                        glowMulticolor = false,
                        glowSingleColor = Color.Red,
                        speedFont = currentSpeedTypeFace,
                        speedometerNumberFont = currentSpeedometerNumberTypeFace,
                        glowRadius = 28f,
                        glowSpeedPoints = glowPoints
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ClickableGlowingCard(
                            glowingColor = Color.Cyan,
                            glowingRadius = 2.dp,
                            modifier = Modifier
                                .size(100.dp)
                                .weight(1f) // Use weight to distribute equally
                                .padding(end = 8.dp), // Add padding to the end (right side) of the card
                            cornersRadius = 10.dp,
                            onClick = {
                                speedoMeterMode = Mode.NORMAL
                                currentSpeedometerNumberTypeFace = typeFaceNUll
                                currentSpeedTypeFace = typeFaceNUll
                                glowPoints=false
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Green),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "NORMAL",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }

                        ClickableGlowingCard(
                            glowingColor = Color.Yellow,
                            glowingRadius = 20.dp,
                            modifier = Modifier
                                .size(100.dp)
                                .weight(1f) // Use weight to distribute equally
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp
                                ), // Add padding to the start (left side) and end (right side) of the card
                            cornersRadius = 10.dp,
                            onClick = { speedoMeterMode = Mode.GLOW
                                currentSpeedometerNumberTypeFace = speedometerNumberFont
                                currentSpeedTypeFace = speedNumberFont
                                glowPoints=true}

                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Yellow),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "GLOW",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }

                        ClickableGlowingCard(
                            glowingColor = Color.Red,
                            glowingRadius = 2.dp,
                            modifier = Modifier
                                .size(100.dp)
                                .weight(1f) // Use weight to distribute equally
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp
                                ), // Add padding to the start (left side) of the card
                            cornersRadius = 10.dp,
                            onClick = { speedoMeterMode = Mode.NEON
                                currentSpeedometerNumberTypeFace = speedometerNumberFont
                                currentSpeedTypeFace = typeFaceNUll
                                glowPoints=true}
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Red),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "NEON",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }

                        ClickableGlowingCard(
                            glowingColor = Color.White,
                            modifier = Modifier
                                .size(100.dp)
                                .weight(1f), // Use weight to distribute equally
                            cornersRadius = 10.dp,
                            glowingRadius = 2.dp,
                            onClick = { speedoMeterMode = Mode.GRADIENT
                                currentSpeedometerNumberTypeFace = typeFaceNUll
                                currentSpeedTypeFace = speedNumberFont
                                glowPoints=true}

                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Blue),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "GRAD",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }


                }


            }


        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = colorResource(id = R.color.white))
        ) {


        }

    }

}

@Preview
@Composable
fun PreviewSpeedometer() {
    SpeedometerUiViewCompose()
}