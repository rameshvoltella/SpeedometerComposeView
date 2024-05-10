package com.ramzmania.speedometerview

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramzmania.speedometerview.ui.theme.SpeedometerComposeViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Keep screen on
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            SpeedometerComposeViewTheme {
                // A surface container using the 'background' color from the theme
                SetStatusBarColor(color = colorResource(id = R.color.black))

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SpeedometerUiViewCompose()
                }
            }
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SpeedometerComposeViewTheme {
////        GlowArc()
//        // Adjust these values as needed
//        val mainColor = Color.Blue
//        val startArcAngle = 0f
//        val endArcAngle = 180f
//        val progress = 0.5f
//
//        // Usage of GlowingArc composable
//        GlowingArc(
//            mainColor = mainColor,
//            startArcAngle = startArcAngle,
//            endArcAngle = endArcAngle,
//            progress = progress,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        )
//    }
//}

