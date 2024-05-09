package com.ramzmania.speedometerview

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.PaintingStyle

import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GlowingCard(
    glowingColor: Color,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    cornersRadius: Dp = 0.dp,
    glowingRadius: Dp = 20.dp,
    xShifting: Dp = 0.dp,
    yShifting: Dp = 0.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .drawBehind {
                val canvasSize = size
                drawContext.canvas.nativeCanvas.apply {
                    drawRoundRect(
                        0f, // Left
                        0f, // Top
                        canvasSize.width, // Right
                        canvasSize.height, // Bottom
                        cornersRadius.toPx(), // Radius X
                        cornersRadius.toPx(), // Radius Y
                        Paint().apply {
                            color = containerColor.toArgb()
                            isAntiAlias = true
                            setShadowLayer(
                                glowingRadius.toPx(),
                                xShifting.toPx(), yShifting.toPx(),
                                glowingColor.copy(alpha = 0.85f).toArgb()
                            )
                        }
                    )
                }
            }
    ) {
        content()
    }
}

/////////////////////////////////////////////////////////////////////

@Composable
fun ClickableGlowingCard(
    glowingColor: Color,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    cornersRadius: Dp = 0.dp,
    glowingRadius: Dp = 20.dp,
    xShifting: Dp = 0.dp,
    yShifting: Dp = 0.dp,
    onClick:() -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .drawBehind {
                val canvasSize = size
                drawContext.canvas.nativeCanvas.apply {
                    drawRoundRect(
                        0f, // Left
                        0f, // Top
                        canvasSize.width, // Right
                        canvasSize.height, // Bottom
                        cornersRadius.toPx(), // Radius X
                        cornersRadius.toPx(), // Radius Y
                        Paint().apply {
                            color = containerColor.toArgb()
                            isAntiAlias = true
                            setShadowLayer(
                                glowingRadius.toPx(),
                                xShifting.toPx(), yShifting.toPx(),
                                glowingColor.copy(alpha = 0.85f).toArgb()
                            )
                        }
                    )
                }
            }
    ) {
        Box(modifier = Modifier.clip(RoundedCornerShape(cornersRadius)).clickable { onClick() }){
            content()
        }
    }
}
@Composable
fun GlowingArc(
    mainColor: Color,
    startArcAngle: Float,
    endArcAngle: Float,
    progress: Float,
    modifier: Modifier = Modifier,
    glowColor: Color = Color.White.copy(alpha = 0.5f), // Adjust alpha to control glow intensity
    glowBlurRadius: Dp = 16.dp // Adjust blur radius for glow effect
) {
    val arcWidth = 16.dp // Set your desired arc width
    val center = remember { mutableStateOf(Offset.Zero) }
    val radius = remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .drawBehind {
                val canvasSize = size
                drawContext.canvas.nativeCanvas.apply {
                    drawArc(
                        color = mainColor,
                        startAngle = startArcAngle,
                        sweepAngle = endArcAngle - startArcAngle,
                        useCenter = false,
                        topLeft = Offset(center.value.x - radius.value, center.value.y - radius.value),
                        size = Size(radius.value * 2, radius.value * 2),
                        style = Stroke(width = arcWidth.toPx())
                    )

                    // Draw the blurred version of the arc for glowing effect
                    drawArc(
                        startAngle = startArcAngle,
                        sweepAngle = endArcAngle - startArcAngle,
                        useCenter = false,
                        topLeft = Offset(center.value.x - radius.value, center.value.y - radius.value),
                        size = Size(radius.value * 2, radius.value * 2),
                        style = Stroke(width = arcWidth.toPx()),
                        alpha = 0.5f, // Adjust alpha to control glow intensity
                        brush = Brush.radialGradient(
                            colors = listOf(glowColor, Color.Transparent),
                            center = center.value,
                            radius = radius.value * 2,
                            tileMode = TileMode.Clamp
                        ),
                        blendMode = BlendMode.Softlight // Adjust blend mode for desired effect
                    )
                }
            }
    ) {
    }
}
