package com.ramzmania.speedometerview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramzmania.speedometerview.ui.theme.SpeedometerComposeViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpeedometerComposeViewTheme {
                // A surface container using the 'background' color from the theme
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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

