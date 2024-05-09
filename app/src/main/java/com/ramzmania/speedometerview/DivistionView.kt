package com.ramzmania.speedometerview


import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramzmania.speedometercomposeview.SpeedometerComposeView

@Composable
fun SpeedometerUiViewCompose() {


    var targetValue by remember { mutableStateOf(0f) }
    var currentSpeed = remember(targetValue) { Animatable(initialValue = 80f) }



    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = colorResource(id = R.color.background_blue))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .wrapContentSize(Alignment.Center)

            ) {
                SpeedometerComposeView(
                    speedMeterMaxRange=220,
                    currentSpeedValue = currentSpeed.value.toInt(),
                    needleColor = Color.Red,
                    speedTextColor = colorResource(
                        id = R.color.white,
                    ),
                    movingSpeedTextColor = Color.White,
                    arcWidth = 50f
                )

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