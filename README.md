# SpeedometerComposeView
This widget is drawn using Compose API only, which makes it work on all platforms that support Compose UI.
Speedometer widget for Compose is an **amazing**, **powerful**, and _multi shape_ :zap: , you can change (colors, bar width, shape, text, font ...everything !!),
[see project on GitHub](https://github.com/rameshvoltella/SpeedometerComposeView).

[![Jit Pack](https://jitpack.io/v/rameshvoltella/SpeedometerComposeView.svg)](https://jitpack.io/#rameshvoltella/SpeedometerComposeView)
[![API](https://img.shields.io/badge/API-+21-red.svg?style=flat)](#)


<div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px;">
<div style="text-align: center;">
        <h3>SPEEDOMETER</h3>
        <img src="appfiles/normal.jpeg" alt="normal" style="width: 40%; height: 40%;">
        <img src="appfiles/glow.jpeg" alt="glow" style="width: 40%; height: 40%;">
    </div>
</div>


Current speedometers available:

|              Normal              |              GLOW              |              NEON              |              GRADIENT              |
|:--------------------------------:|:------------------------------:|:------------------------------:|:----------------------------------:|
| <img src="images/normal.jpeg" /> | <img src="images/glow.jpeg" /> | <img src="images/neon.jpeg" /> | <img src="images/gradient.jpeg" /> |

### Downloads

1. Download the APK file from
   here [DOWNLOAD](https://github.com/rameshvoltella/SpeedometerComposeView/raw/main/appfiles/SpeedometerCompose_v3.apk)

## Video Preview

Full Mode-> https://www.youtube.com/shorts/ZCHQigQF8ac

Glow Mode-> https://www.youtube.com/shorts/3mPNlcWVTmw

Requirements to use:

- Minimum API version `21` 

Add `Jit Pack` to your dependency management.

```kotlin
	dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Then, add the speedometer dependencies according to your project:

## Compose To Your Project

Go to `build.gradle.kts`

```kotlin
dependencies {
    implementation("com.github.rameshvoltella:SpeedometerComposeView:3.0.0")
}
```

## USAGE

This just a sample you can use however you want based on the Parameters sheet below

```kotlin
SpeedometerComposeView(
    speedMeterMaxRange = 220,
    currentSpeedValue = 80,
    needleColor = Color.Red,
    speedTextColor = colorResource(
        id = R.color.white,
    ),
    movingSpeedTextColor = Color.White,
    arcWidth = 50f,
    speedometerMode = Mode.NORMAL,
    glowMulticolor = false,
    glowSingleColor = Color.Red,
    speedFont = ResourcesCompat.getFont(context, R.font.font_speed),
    speedometerNumberFont = ResourcesCompat.getFont(context, R.font.font_speed_digits),
    glowRadius = 28f,
    glowSpeedPoints = true,
    baseArcColorConstant = Color(0x33FF0000)
)
```

## Parameters

<table>
  <tr>
    <th>Parameter</th>
    <th>Description</th>
    <th>Data Type</th>
  </tr>
  <tr>
    <td>currentSpeedValue</td>
    <td>The current speed value</td>
    <td>Int</td>
  </tr>
  <tr>
    <td>speedMeterMaxRange</td>
    <td>The maximum range of the speed meter</td>
    <td>Int</td>
  </tr>
  <tr>
    <td>startColorRange</td>
    <td>The start color for the speed range</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>startColorRangeSecondary</td>
    <td>The secondary start color for the speed range</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>mediumColorRange</td>
    <td>The medium color for the speed range</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>mediumColorRangeSecondary</td>
    <td>The secondary medium color for the speed range</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>endColorRange</td>
    <td>The end color for the speed range</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>endColorRangeSecondary</td>
    <td>The secondary end color for the speed range</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>needleColor</td>
    <td>The color of the needle</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>speedTextColor</td>
    <td>The color of the speed text</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>movingSpeedTextColor</td>
    <td>The color of the moving speed text</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>speedFont</td>
    <td>The font for the speed text</td>
    <td>Typeface?</td>
  </tr>
  <tr>
    <td>speedometerNumberFont</td>
    <td>The font for the speedometer number</td>
    <td>Typeface?</td>
  </tr>
  <tr>
    <td>arcWidth</td>
    <td>The width of the arc</td>
    <td>Float</td>
  </tr>
  <tr>
    <td>speedometerMode</td>
    <td>The mode of the speedometer</td>
    <td>Mode</td>
  </tr>
  <tr>
    <td>gradientColorList</td>
    <td>The list of colors for the gradient</td>
    <td>List&lt;Color&gt;</td>
  </tr>
  <tr>
    <td>gradientType</td>
    <td>The type of gradient</td>
    <td>GradientType</td>
  </tr>
  <tr>
    <td>neonColor</td>
    <td>The color for neon effect</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>neonCenterColor</td>
    <td>The center color for neon effect</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>glowMulticolor</td>
    <td>Whether to use multicolor glow effect</td>
    <td>Boolean</td>
  </tr>
  <tr>
    <td>glowSingleColor</td>
    <td>The single color for glow effect</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>glowRadius</td>
    <td>The radius of the glow effect</td>
    <td>Float</td>
  </tr>
  <tr>
    <td>glowSpeedPoints</td>
    <td>Whether to use speed points in the glow effect</td>
    <td>Boolean</td>
  </tr>
  <tr>
    <td>baseArcColorConstant</td>
    <td>The base color for the arc</td>
    <td>Color?</td>
  </tr>
  <tr>
    <td>needleCircleColor</td>
    <td>The color for the needle circle</td>
    <td>Color?</td>
  </tr>
  <tr>
    <td>needleIndicatorColor</td>
    <td>The color for the needle indicator</td>
    <td>Color?</td>
  </tr>
  <tr>
    <td>needleSemiIndicatorColor</td>
    <td>The color for the semi needle indicator</td>
    <td>Color</td>
  </tr>
  <tr>
    <td>movingSpeedTextExtraPadding</td>
    <td>Extra padding for the moving speed text</td>
    <td>Float</td>
  </tr>
</table>

## ARC MODES

<table>
  <tr>
    <th>Mode</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>NORMAL</td>
    <td>Draws a solid arc with a single color.</td>
  </tr>
  <tr>
    <td>GLOW</td>
    <td>Draws an arc with a glowing effect.</td>
  </tr>
  <tr>
    <td>GRADIENT</td>
    <td>Draws an arc with a gradient effect.</td>
  </tr>
  <tr>
    <td>NEON</td>
    <td>Draws an arc with a neon-like effect.</td>
  </tr>
</table>

## GRADIENT ARC MODE - TYPES

<table>
  <tr>
    <th>GradientType</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>LINEAR</td>
    <td>Draws a linear gradient.</td>
  </tr>
  <tr>
    <td>HORIZONTAL</td>
    <td>Draws a horizontal gradient.</td>
  </tr>
  <tr>
    <td>VERTICAL</td>
    <td>Draws a vertical gradient.</td>
  </tr>
  <tr>
    <td>RADIAL</td>
    <td>Draws a radial gradient.</td>
  </tr>
  <tr>
    <td>SWEEP</td>
    <td>Draws a sweep gradient.</td>
  </tr>
</table>


## Speedometer using specific size

Sometimes there may size issue in that case you can use a box to specific size

```kotlin
Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    // Spacer for top margin
    Spacer(modifier = Modifier.height(16.dp))

    // Title
    Text(
        text = "Speedometer using specific size",
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    )

    // Spacer for vertical space between title and SpeedometerComposeView
    Spacer(modifier = Modifier.height(16.dp))

    // SpeedometerComposeView
    Box(
        modifier = Modifier
            .width(450.dp)
            .height(450.dp)
            .fillMaxSize() // Center the Box within the Column
    ) {
        SpeedometerComposeView(
            speedMeterMaxRange = 80,
            currentSpeedValue = speedOver.value.toInt(),
            needleColor = Color.Red,
            speedTextColor = colorResource(id = R.color.white),
            movingSpeedTextColor = Color.White,
            arcWidth = 50f,
            speedometerMode = speedoMeterMode,
            glowMulticolor = false,
            glowSingleColor = Color.Red,
            speedFont = currentSpeedTypeFace,
            speedometerNumberFont = currentSpeedometerNumberTypeFace,
            glowRadius = 28f,
            glowSpeedPoints = glowPoints,
            baseArcColorConstant = basicArcColor
        )
    }
}
```
## MY GITHUB TROPHY

[![trophy](https://github-profile-trophy.vercel.app/?username=rameshvoltella)](https://github.com/ryo-ma/github-profile-trophy)

## Contributing

I am welcome contributions from the community to improve the SpeedometerComposeView

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/improvement`).
3. Make your changes and commit them (`git commit -am 'Add feature/improvement'`).
4. Push to the branch (`git push origin feature/improvement`).
5. Create a new Pull Request.

