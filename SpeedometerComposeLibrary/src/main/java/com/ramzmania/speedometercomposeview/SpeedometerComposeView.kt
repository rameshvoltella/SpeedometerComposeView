package com.ramzmania.speedometercomposeview
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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


@Composable
fun SpeedometerComposeView(
    rangeValue: Int=0,
    speedMeterRange: Int = 220,
    startColorRange: Color = Color(0xFF388E3C),
    startColorRangeSecondary: Color =Color(0xFFC8E6C9),
    mediumColorRange: Color = Color(0xFFF57C00),
    mediumColorRangeSecondary: Color =Color(0xFFFFE0B2),
    endColorRange: Color = Color(0xFFD32F2F),
    endColorRangeSecondary: Color = Color(0xFFC8E6C9),
    needleColor: Color = Color.Black,
    speedTextColor: Color = Color.Black,
    movingSpeedTextColor: Color = Color.Black,
    speedText:String="0 Km/Hr"
) {
    val arcDegrees = 275
    val startArcAngle = 135f
    val startStepAngle = -45
    val numberOfMarkers = 55
    val degreesMarkerStep = arcDegrees / numberOfMarkers
//    val numberOfPoints = 12
//    val step = 20// Adjust for 1 less step
//    val points = (0 until numberOfPoints - 1).map { it * step } + speedoMeterRange // Add the range as the last point
//    val adjustedPoints = (0 until numberOfPoints - 1).map { it * step } + speedoMeterRange
// Adjust the values to end with the last digit 0
//    val adjustedPoints = points.map { if (it < speedoMeterRange) it / 10 * 10 else speedoMeterRange }
//    val adjustedPoints = mutableListOf<Int>()
    val progress = if(rangeValue>speedMeterRange)
    {
        seekPercentage(speedMeterRange,55,speedMeterRange)
    }else
    {
        seekPercentage(rangeValue,55,speedMeterRange)

    }
    // Loop through the points and extract the values of every fifth point
   /* for (i in 0 until numberOfMarkers) {
        if ((i + 1) % 5 == 0) {  // Check if the point is a multiple of 5
            val pointValue = ((i + 1) * (speedoMeterRange.toDouble() / numberOfMarkers)).toInt()
            adjustedPoints.add(pointValue)
        }
    }

    // Adjusting the list to start from index 0
    adjustedPoints.add(0, 0)*/
    val adjustedPoints = (5..numberOfMarkers step 5)
        .map { (it * (speedMeterRange.toDouble() / numberOfMarkers)).toInt() }
        .toMutableList() // Convert to mutable list
        .apply { add(0, 0) } // Add zero value at index 0

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
                    progress < 40 ->mediumColorRange to mediumColorRangeSecondary
                    else -> endColorRange to endColorRangeSecondary
                }
                val paint = Paint().apply {
                    color = mainColor
                }
                val centerArcSize = Size(w *0.9f, h *0.9f)
                val centerArcStroke = Stroke(20f, 0f, StrokeCap.Round)

                drawArc(
                    secondaryColor,
                    startArcAngle,
                    arcDegrees.toFloat(),
                    false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawArc(
                    mainColor,
                    startArcAngle,
                    (degreesMarkerStep * progress).toFloat(),
                    false,
                    topLeft = quarterOffset,
                    size = centerArcSize,
                    style = centerArcStroke
                )

                drawCircle(mainColor, 50f, centerOffset)
                drawCircle(Color.White, 25f, centerOffset)
                drawCircle(needleColor, 20f, centerOffset)
                var conterEnter=0
                for ((counter, degrees) in (startStepAngle..(startStepAngle + arcDegrees) step degreesMarkerStep).withIndex()) {
                    val lineEndX = 100f
                    val lineEndY = h / 2f // Since line is horizontal, lineEndY is the same as the center of the canvas vertically
                    paint.color = mainColor
//                    val lineStartX = if (counter % 5 == 0) {
//                        paint.strokeWidth = 3f
//                        0f
//                    } else {
//                        paint.strokeWidth = 1f
//                        lineEndX * .2f
//                    }

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

                    /*To right*/
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
                    /*To left*/

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
                    if (counter % 5 == 0) {
                        Log.d("contercame","cou"+conterEnter+"mmm"+adjustedPoints)

//                        val text="10"
                        val text =
                            adjustedPoints[conterEnter] // You can replace this with your desired text
//                             textIndex++
                        conterEnter += 1

                        val textPaint = android.graphics.Paint().apply {
                            color = speedTextColor.toArgb()
                            textSize = 30f // Adjust text size as needed
                        }


                        canvas.save()
                        val marginFromTop: Float
                        val marginLeft: Float
                        val textX: Float
                        val textY = lineEndY + 10f

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

                        canvas.nativeCanvas.drawText(text.toString(), textX - marginLeft, textY, textPaint)
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
//                val text = calculateRangeValue(progress,(1.0f * 55).toInt(),speedoMeterRange).toString()+" km/Hr" // Replace with your desired text
                val textPaint = android.graphics.Paint().apply {
                    color = movingSpeedTextColor.toArgb()
                    textSize = 80f // Adjust text size as needed
                    isFakeBoldText = true // Make the text bold
                }
                val textWidth = textPaint.measureText(speedText)
                val textX = (drawContext.size.width - textWidth) / 2 // Center the text horizontally
                val textY = drawContext.size.height * 0.9f // Position the text below the drawing
                canvas.nativeCanvas.drawText(speedText, textX, textY, textPaint)
                conterEnter=0;
            }
        }
    )
}
fun calculateRangeValue(seekBarValue: Int, seekBarMaxValue: Int, rangeMaxValue: Int): Int {
    return ((seekBarValue.toFloat() / seekBarMaxValue.toFloat()) * rangeMaxValue).toInt()
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//        val data = calculateSeekBarValue(200.0, 250, 55)
//        val seekBarPosition = calculateSeekBarPosition(210, seekBarMaxValue)
        val totalValue = 800
//        val numPoints = 55
//        val givenValue = 220

       /* // Calculate the value of each point
        val pointValue = totalValue.toDouble() / numPoints

        // Calculate the point index where the given value lies
        val pointIndex = (givenValue / pointValue).toInt()

        // Calculate the values of the two neighboring points
        val lowerPointValue = pointIndex * pointValue
        val upperPointValue = (pointIndex + 1) * pointValue

        // Choose the closest point index to the given value
        val closestPointIndex = if (givenValue - lowerPointValue < upperPointValue - givenValue) {
            pointIndex
        } else {
            pointIndex + 1
        }*/
//        val pointValue = totalValue.toDouble() / numPoints
//        val pointIndex = (givenValue / pointValue).toInt()
//
//        val closestPointIndex = if (givenValue % pointValue < pointValue / 2) pointIndex else pointIndex + 1

        SpeedometerComposeView(rangeValue = 363, speedMeterRange = totalValue)

}
//fun calculateSeekBarValue(rangeValue: Double, rangeMaxValue: Int, seekBarMaxValue: Int): Int {
//    return ((rangeValue.toFloat() / rangeMaxValue.toFloat()) * seekBarMaxValue).toInt()
//}
fun calculateSeekBarValue(rangeValue: Double, rangeMaxValue: Int, seekBarMaxValue: Int): Int {
    val step = rangeMaxValue / seekBarMaxValue.toDouble()
    return (rangeValue / step).toInt()
}
val numberOfPoints = 12 // Or any desired number of points
val step = 20 // Or 30
val speedoMeterRange = 250 // Or any desired value for the last point
val seekBarMaxValue = 55 // Maximum value for the seek bar

val points = (0 until numberOfPoints - 1).map { it * step } + speedoMeterRange

fun calculateSeekBarPosition(value: Int, maxValue: Int): Int {
    val position = points.indexOfFirst { it >= value }
    return if (position == -1) {
        maxValue // If value is greater than the maximum value, return maxValue
    } else {
        (position.toDouble() / points.lastIndex * maxValue).toInt()
    }
}
fun seekPercentage(rangeValue: Int, rangeMaxValue: Int, seekBarMaxValue: Int): Int {
    val pointValue = seekBarMaxValue.toDouble() / rangeMaxValue
    val pointIndex = (rangeValue / pointValue).toInt()

    val closestPointIndex = if (rangeValue % pointValue < pointValue / 2) pointIndex else pointIndex + 1
    return closestPointIndex

}
