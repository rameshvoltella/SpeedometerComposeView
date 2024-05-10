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

