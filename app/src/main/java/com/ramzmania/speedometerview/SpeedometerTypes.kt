package com.ramzmania.speedometerview


import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.ramzmania.speedometercomposeview.Mode
import com.ramzmania.speedometercomposeview.SpeedometerComposeView
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeedometerUiViewCompose() {


    var targetValue by remember { mutableStateOf(0f) }
//    var currentSpeed = remember(targetValue) { Animatable(initialValue = 220f) }
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


    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(
            context
        )
    }

    val permissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val permissionsState = rememberMultiplePermissionsState(permissions = permissions)
    val allPermissionsGranted = permissionsState.permissions.all { it.status.isGranted }
//    val speedProgress = remember { mutableStateOf(0) }
    var speedOver = remember(targetValue) { Animatable(initialValue = 0f) }

    var targetAnimationValue by remember {
        mutableStateOf(10f)
    }
    val scope = rememberCoroutineScope()
    var speedMessage by remember {
        mutableStateOf("Pedal..")
    }
    var speedtext by remember {
        mutableStateOf("0 Km/Hr")
    }

    var roadLineColor by remember {
        mutableStateOf(Color.White)
    }


    val offsetY = rememberInfiniteTransition(label = "animationcar").animateFloat(
        initialValue = 0f,
        targetValue = targetAnimationValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000), // Adjust duration as needed
            repeatMode = RepeatMode.Reverse
        ), label = "animationcar"
    )

    DisposableEffect(Unit) {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
            .setWaitForAccurateLocation(true)
            .setMinUpdateIntervalMillis(1000)
            .setMaxUpdateDelayMillis(1000)
            .build()
        val locationCallback = object : LocationCallback() {
            @SuppressLint("SetTextI18n")
            override fun onLocationResult(locationResult: LocationResult) {
                Log.d("localoo","yess")
                for (location in locationResult.locations) {
                    // Calculate distance if needed

                    val speed = location.speed // Speed in meters/second

                    // Calculate speed
                    val speedKmH = speed * 3.6 // Convert speed to km/h
//                    val speedKmH = 210.0// Convert speed to km/h
//                    val speedKmH=55
//                    val speedKmH=200
                    speedtext=String.format("%.1f", speed * 3.6)+" Km/Hr"
//                            val data = seekPercentage(speedKmH.toInt(), 55, MAX_SPEED)
                    scope.launch {
                        speedOver.animateTo(
                            targetValue = speedKmH.toFloat(),
                            animationSpec = tween(
                                durationMillis = 1000,
                                easing = FastOutLinearInEasing
                            )
                        )
                    }
                    if(speedKmH<1)
                    {
                        roadLineColor= Color.White
                        speedMessage="Pedal.."
                        targetAnimationValue=10f
                    }
                    else if(speedKmH<80)
                    {
                        roadLineColor= Color(0xFF388E3C)
                        speedMessage="Nice.."
                        targetAnimationValue=30f

                    }
                    else if(speedKmH<90)
                    {
                        roadLineColor= Color(0xFFF57C00)
                        speedMessage="Watch out.."
                        targetAnimationValue=40f

                    }else
                    {
                        roadLineColor= Color(0xFFD32F2F)
                        speedMessage="Rash..."
                        targetAnimationValue=50f

                    }

//                        progress.value= data.toFloat()
//                        CoroutineScope(Dispatchers.IO).launch {
//
//                            progress.animateTo(
//                                targetValue = data.toFloat(),
//                                animationSpec = tween(
//                                    durationMillis = 1000,
//                                    easing = FastOutLinearInEasing
//                                )
//                            )
//                        }





                    // Update UI based on speed

                }
            }
        }

        // Request location updates
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null /* Looper */
            )
        }else
        {
            permissionsState.launchMultiplePermissionRequest()
        }


        // Cleanup when not needed
        onDispose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
            Log.d("localoo","over")

        }
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
                    Spacer(modifier = Modifier.height(16.dp)) // Adjust the height as needed for the top margin
                    SpeedometerComposeView(
                        speedMeterMaxRange = 220,
                        currentSpeedValue = speedOver.value.toInt(),
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
                .weight(1.1f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(color = colorResource(id = R.color.white))
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight() // Fill the available height
                    .width(200.dp) // Set fixed width
                    .background(color = Color.Black)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = roadLineColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = roadLineColor)
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = roadLineColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = roadLineColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = roadLineColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = roadLineColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = roadLineColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .width(30.dp)
                            .padding(8.dp)
                            .background(color = roadLineColor)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier.offset(y = offsetY.value.dp)
                    ) {
                        Image(

                            painter = painterResource(id = R.drawable.car),
                            contentDescription = "Your Image",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                                .align(Alignment.Center)

                        )
                    }
                }
                /*modifier = Modifier
                    .width(400.dp)
                    .height(300.dp)
                    .padding(8.dp)
                    .align(Alignment.Center)
                    .background(color = Color.Green)*/
            }
        }

    }

}

@Preview
@Composable
fun PreviewSpeedometer() {
    SpeedometerUiViewCompose()
}