package com.ramzmania.speedometercomposeview

/**
 * Author: Ramesh M Nair
 * Certificate of Authenticity: This code is authored by Ramesh M Nair.
 * Date: 08/06/2024
 * Description: This code provides a custom Composable function to render a speedometer view using Jetpack Compose.
 */
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.rotate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Composable function to render a speedometer view.
 *
 * @param currentSpeedValue The current speed value.
 * @param speedMeterMaxRange The maximum range of the speedometer.
 * @param startColorRange The start color of the speedometer range.
 * @param startColorRangeSecondary The secondary start color of the speedometer range.
 * @param mediumColorRange The medium color of the speedometer range.
 * @param mediumColorRangeSecondary The secondary medium color of the speedometer range.
 * @param endColorRange The end color of the speedometer range.
 * @param endColorRangeSecondary The secondary end color of the speedometer range.
 * @param needleColor The color of the speedometer needle.
 * @param speedTextColor The color of the speed text.
 * @param movingSpeedTextColor The color of the moving speed text.
 */
@Composable
fun SpeedometerComposeView(
    currentSpeedValue: Int = 0,
    speedMeterMaxRange: Int = 220,
    startColorRange: Color = Color(0xFF388E3C),
    startColorRangeSecondary: Color = Color(0xFFC8E6C9),
    mediumColorRange: Color = Color(0xFFF57C00),
    mediumColorRangeSecondary: Color = Color(0xFFFFE0B2),
    endColorRange: Color = Color(0xFFD32F2F),
    endColorRangeSecondary: Color = Color(0xFFC8E6C9),
    needleColor: Color = Color.Black,
    speedTextColor: Color = Color.Black,
    movingSpeedTextColor: Color = Color.Black,
    speedFont: Typeface? = null,
    speedometerNumberFont: Typeface? = null,
    arcWidth: Float = 20f,
    speedometerMode: Mode = Mode.NORMAL,
    gradientColorList:List<Color> = listOf(startColorRange, mediumColorRange, endColorRange),
    gradientType: GradientType=GradientType.HORIZONTAL,
    neonColor: Color=Color.Red,
    neonCenterColor:Color= Color.White,
    glowMulticolor:Boolean=true,
    glowSingleColor: Color = Color(0xFF388E3C),
    glowRadius:Float=28f,
    glowSpeedPoints:Boolean=false,
    baseArcColorConstant:Color?=null,
    needleCircleColor:Color?=null,
    needleIndicatorColor:Color?=null,
    needleSemiIndicatorColor:Color=Color.White,
    movingSpeedTextExtraPadding:Float=14f
) {

    // Constants for drawing the speedometer
    val arcDegrees =
        275  //The total degrees of the arc that represents the entire range of the speedometer. It's set to 275, meaning the speedometer arc spans 275 degrees.
    val startArcAngle =
        135f //The starting angle of the arc that represents the speedometer. It's set to 135 degrees.
    val startStepAngle =
        -45 //The starting angle for drawing the markers on the speedometer. It's set to -45 degrees
    val numberOfMarkers =
        55 //    The number of markers to be drawn on the speedometer. It's set to 55.

    val degreesMarkerStep = arcDegrees / numberOfMarkers

    // Calculate the progress based on the current speed value
    val progress = if (currentSpeedValue > speedMeterMaxRange) {
        seekPercentage(speedMeterMaxRange, numberOfMarkers, speedMeterMaxRange)
    } else {
        seekPercentage(currentSpeedValue, numberOfMarkers, speedMeterMaxRange)

    }

    // Calculate adjusted points for markers
    val adjustedPoints = (5..numberOfMarkers step 5)
        .map { (it * (speedMeterMaxRange.toDouble() / numberOfMarkers)).toInt() }
        .toMutableList() // Convert to mutable list
        .apply { add(0, 0) } // Add zero value at index 0

    // Draw the speedometer using Canvas
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onDraw = {
            drawIntoCanvas { canvas ->
                val w = drawContext.size.width
                val h = drawContext.size.height
                val centerOffset = Offset(w / 2f, h / 2f)
                val quarterOffset = Offset(w / 20f, h / 20f)

                val (mainColor, secondaryColor) = when {
                    progress < 20 -> startColorRange to startColorRangeSecondary
                    progress < 40 -> mediumColorRange to mediumColorRangeSecondary
                    else -> endColorRange to endColorRangeSecondary
                }
                val paint = Paint().apply {
                    color = mainColor
                }
                val paintGlow = Paint().apply {
                    color = mainColor
                }
                val centerArcSize = Size(w * 0.9f, h * 0.9f)
                val centerArcStroke = Stroke(arcWidth, 0f, StrokeCap.Round)
                val centerGradientArcStroke = Stroke(arcWidth - (arcWidth / 2), 0f, StrokeCap.Round)

                // Draw the background arc
                drawArc(
                    baseArcColorConstant ?: secondaryColor,
                    startArcAngle,
                    arcDegrees.toFloat(),
                    false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                when (speedometerMode) {
                    Mode.NORMAL -> {
                        drawArc(
                            mainColor,
                            startArcAngle,
                            (degreesMarkerStep * progress).toFloat(),
                            false,
                            topLeft = quarterOffset,
                            size = centerArcSize,
                            style = centerArcStroke
                        )
                    }
                    Mode.GLOW -> {
                        var glowColor=mainColor;

                        if(!glowMulticolor) {
                            glowColor=glowSingleColor
                        }


                        val frameworkPaint = paintGlow.asFrameworkPaint()
                        frameworkPaint.style = android.graphics.Paint.Style.STROKE
                        frameworkPaint.color = glowColor.toArgb()
                        frameworkPaint.strokeWidth = arcWidth
                        frameworkPaint.strokeCap = android.graphics.Paint.Cap.ROUND
                        //this is not in skia paint
                        frameworkPaint.setShadowLayer(glowRadius, 0f, 0f, glowColor.toArgb())
                        val width = centerArcSize.width
                        val height = centerArcSize.height
                        val rect = Rect(
                            left = w / 20f,
                            top = h / 20f,
                            right = w / 20f + width,
                            bottom = h / 20f + height
                        )
                        canvas.drawArc(
                            rect = rect,
                            startArcAngle,
                            (degreesMarkerStep * progress).toFloat(),
                            false,
                            paintGlow
                        )
                        drawArc(
                            glowColor,
                            startArcAngle,
                            (degreesMarkerStep * progress).toFloat(),
                            false,
                            topLeft = quarterOffset,
                            size = centerArcSize,
                            style = centerArcStroke
                        )
                    }
                    Mode.NEON -> {
                        drawArc(
                            Brush.radialGradient(
                                listOf(neonColor.copy(.3f), neonColor, neonColor.copy(.3f)),
                                center = Offset(300f, 300f),
                                radius = 500f
                            ),
                            startArcAngle,
                            (degreesMarkerStep * progress).toFloat(),
                            false,
                            topLeft = quarterOffset,
                            size = centerArcSize,
                            style = centerArcStroke
                        )
                        drawArc(
                            neonCenterColor,
                            startArcAngle,
                            (degreesMarkerStep * progress).toFloat(),
                            false,
                            topLeft = quarterOffset,
                            size = centerArcSize,
                            style = centerGradientArcStroke
                        )
                    }
                    Mode.GRADIENT -> {

                        var brushGradient= if(gradientType==GradientType.HORIZONTAL)
                        {
                            Brush.horizontalGradient(gradientColorList)
                        }else if(gradientType==GradientType.LINEAR)
                        {
                            Brush.linearGradient(gradientColorList)

                        }else if(gradientType==GradientType.RADIAL)
                        {
                            Brush.radialGradient(gradientColorList)

                        }else if(gradientType==GradientType.VERTICAL)
                        {
                            Brush.verticalGradient(gradientColorList)

                        }else
                        {
                            Brush.sweepGradient(gradientColorList)

                        }
                        drawArc(
                            brush = brushGradient,
                            startArcAngle,
                            (degreesMarkerStep * progress).toFloat(),
                            false,
                            topLeft = quarterOffset,
                            size = centerArcSize,
                            style = centerArcStroke
                        )
                    }
                }


                // Draw circles for the center and needle
                drawCircle(needleCircleColor ?: mainColor, 50f, centerOffset)
                drawCircle(needleSemiIndicatorColor, 25f, centerOffset)
                drawCircle(needleColor, 20f, centerOffset)
//
//                // Draw markers and their text the 55 marker points
                var counterPoint = 0
                for ((counter, degrees) in (startStepAngle..(startStepAngle + arcDegrees) step degreesMarkerStep).withIndex()) {
                    val lineEndX = 100f
                    val lineEndY =
                        h / 2f // Since line is horizontal, lineEndY is the same as the center of the canvas vertically
                    paint.color = needleIndicatorColor ?: mainColor

                    val lineStartX = if (counter % 5 == 0) {
                        paint.strokeWidth = 3f
                        lineEndX - 15f
                    } else {
                        paint.strokeWidth = 1f
                        lineEndX - 7.5f
                    }
                    canvas.save()
                    canvas.rotate(degrees.toFloat(), w / 2f, h / 2f)
                    canvas.drawLine(
                        Offset(lineStartX, h / 2f),
                        Offset(lineEndX, h / 2f),
                        paint
                    )

                    /*To right if you want to change the small points inside*/
                    /*    if (counter % 5 == 0) {
                            val text = "Your Text Here" // You can replace this with your desired text
                            val textPaint = android.graphics.Paint().apply {
                                color = Color.Black.toArgb()
                                textSize = 16f // Adjust text size as needed
                            }

                            val textWidth = textPaint.measureText(text)
                            val textX = lineEndX + 10f // Position the text to the right of the line
                            val marginFromTop = 12f // Adjust margin as needed
                            val textY = lineEndY - textPaint.textSize / 2 + marginFromTop // Center the text vertically with margin

                            canvas.nativeCanvas.drawText(text, textX, textY, textPaint)
                        }*/
                    /*To left if you want to change the small points inside*/

                    /*  if (counter % 5 == 0) {
                          val text = "Your Text Here" // You can replace this with your desired text
                          val textPaint = android.graphics.Paint().apply {
                              color = Color.Black.toArgb()
                              textSize = 16f // Adjust text size as needed
                          }

                          val textWidth = textPaint.measureText(text)
                          val marginFromTop = 10f // Adjust margin as needed
                          val textX = lineEndX - textWidth - 10f // Position the text to the left of the line
                          val textY = lineEndY - textPaint.textSize / 2 + marginFromTop // Center the text vertically with margin

                          canvas.nativeCanvas.drawText(text, textX, textY, textPaint)
                      }*/


                    /*
                    * Here we set the numbers based on the adjustedPoints on each 5 points from max speed
                     like what you see in 0,20,40,... etc *  */
                    if (counter % 5 == 0) {

                        val text =
                            adjustedPoints[counterPoint] // The point or text value to show in main needle
                        counterPoint += 1

                        val textPaint = android.graphics.Paint().apply {
                            color = speedTextColor.toArgb()
                            textSize = 25f // Adjust text size as needed
                            speedometerNumberFont?.let { typeface = it }
                        }

//                        val speedFrameworkPaint = textPaint
                        if(glowSpeedPoints)
                        {

                            textPaint.style = android.graphics.Paint.Style.STROKE
                            textPaint.color = speedTextColor.toArgb()
                            textPaint.strokeWidth = 3f
                        //this is not in skia paint
                            textPaint.setShadowLayer(glowRadius, 0f, 0f, speedTextColor.toArgb())

                        }
                        canvas.save()
                        val marginFromTop: Float
                        val marginLeft: Float
                        val textX: Float
                        var textY = lineEndY + 10f
                        if(text>99)
                        {
                            textY += movingSpeedTextExtraPadding
                        }
                        /*Rotate canvas so that the point perfectly pad with the circle*/
                        when {
                            counter > 40 -> {
                                marginFromTop = 60f
                                marginLeft = 0f
                                textX = lineEndX - textPaint.textSize / 2 + marginFromTop
                                canvas.rotate(-90f, textX, textY)
                            }

                            counter in 11..20 -> {
                                marginFromTop = 70f
                                marginLeft = 0f
                                textX = lineEndX - textPaint.textSize / 2 + marginFromTop
                                canvas.rotate(-90f, textX, textY)
                            }

                            counter > 20 -> {
                                marginFromTop = 60f
                                marginLeft = 0f
                                textX = lineEndX - textPaint.textSize / 2 + marginFromTop
                                canvas.rotate(-90f, textX, textY)
                            }

                            else -> {
                                marginFromTop = 80f
                                marginLeft = 40f
                                textX = lineEndX - textPaint.textSize / 2 + marginFromTop
                            }
                        }

                        canvas.nativeCanvas.drawText(
                            text.toString(),
                            textX - marginLeft,
                            textY,
                            textPaint
                        )
                        canvas.restore()
                    }

                    if (counter == progress) {
                        paint.color = needleColor
                        canvas.drawPath(
                            Path().apply {
                                moveTo(w / 2, (h / 2) - 15)
                                lineTo(w / 2, (h / 2) + 15)
                                lineTo(w / 5.5f, h / 2)
                                lineTo(w / 2, (h / 2) - 15)
                                close()
                            },
                            paint
                        )
                    }
                    canvas.restore()
                }
                // Draw text at the center of the view
                val textPaint = android.graphics.Paint().apply {
                    color = movingSpeedTextColor.toArgb()
                    textSize = 80f // Adjust text size as needed
                    speedFont?.let { typeface = it } // Set custom font if not null
                }
                val currentSpeed = "$currentSpeedValue Km/Hr"
                val textWidth = textPaint.measureText(currentSpeed)
                val textX = (drawContext.size.width - textWidth) / 2 // Center the text horizontally
                val textY = drawContext.size.height * 0.9f // Position the text below the drawing
                canvas.nativeCanvas.drawText(currentSpeed, textX, textY, textPaint)
                counterPoint = 0;
            }
        }
    )
}


/*
* Calculate the percentage of needle point to show based on the 55 point
* */
fun seekPercentage(rangeValue: Int, rangeMaxValue: Int, seekBarMaxValue: Int): Int {
    val pointValue = seekBarMaxValue.toDouble() / rangeMaxValue
    val pointIndex = (rangeValue / pointValue).toInt()

    val closestPointIndex =
        if (rangeValue % pointValue < pointValue / 2) pointIndex else pointIndex + 1
    return closestPointIndex

}

@Preview(showBackground = true)
@Composable
fun SpeedPreview() {

    val totalValue = 800

    SpeedometerComposeView(currentSpeedValue = 363, speedMeterMaxRange = totalValue)

}

@Composable
fun GlowCircle() {

    Canvas(
        modifier = Modifier.size(120.dp),
        onDraw = {

            val center = size.minDimension / 2f

            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.style = android.graphics.Paint.Style.STROKE
            frameworkPaint.color = 200
            frameworkPaint.strokeWidth = 8f

            //this is not in skia paint
            frameworkPaint.setShadowLayer(28f, 0f, 0f, 200)

            drawIntoCanvas {
                it.drawCircle(center = Offset(center, center), radius = center - 30f, paint = paint)
            }

        }
    )

}