# SpeedometerComposeView
This widget is drawn using Compose API only, which makes it work on all platforms that support Compose UI.
Speedometer widget for Compose. **amazing**, **powerful**, and _multi shape_ :zap: , you can change (colors, bar width, shape, text, font ...everything !!),
[see project on GitHub](https://github.com/rameshvoltella/SpeedometerComposeView).

[![Jit Pack](https://jitpack.io/v/rameshvoltella/SpeedometerComposeView.svg)](https://jitpack.io/#rameshvoltella/SpeedometerComposeView)
[![API](https://img.shields.io/badge/API-+21-red.svg?style=flat)](#)


<div style="display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px;">
<div style="text-align: center;">
        <h3>SPEEDOMETER</h3>
        <img src="https://github.com/rameshvoltella/SpeedometerComposeView/blob/main/appfiles/normal.jpeg?raw=true" alt="Tracker OFF" style="width: 40%; height: 40%;">
        <img src="https://github.com/rameshvoltella/SpeedometerComposeView/blob/main/appfiles/glow.jpeg?raw=true" alt="Tracker ON" style="width: 40%; height: 40%;">
    </div>
</div>

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


