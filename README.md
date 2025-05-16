[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
![Maven Central Version](https://img.shields.io/maven-central/v/io.github.aughtone/aughtone-format?style=flat)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.10-blue.svg?logo=kotlin&style=flat)](http://kotlinlang.org)
[![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-brightgreen?logo=kotlin)](https://github.com/JetBrains/compose-multiplatform)


![badge-android](http://img.shields.io/badge/platform-android-6EDB8D.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-CDCDCD.svg?style=flat)
![badge-desktop](http://img.shields.io/badge/platform-desktop-DB413D.svg?style=flat)
![badge-js](http://img.shields.io/badge/platform-js%2Fwasm-FDD835.svg?style=flat)


# DateTime Format

This library for set up for [Kotlin Multiplatform](https://www.jetbrains.com/kotlin-multiplatform/) (KMP)

Feel free to fork it and make improvements, I'll keep up as best I can.

_Note: this library currently uses nl.jacobras:Human-Readable for relative time, however it is not 
all tha compatible and as time goes on, we expect to replace it._

# Installation
![Maven Central Version](https://img.shields.io/maven-central/v/io.github.aughtone/aughtone-format?style=flat)

## Datetime Formatting
```gradle
implementation("io.github.aughtone:format-datetime:${version}")
```

# Usage & Examples

#### String Formatting

This function is meant as a replacement for the string format function in java.

_The intention is to improve string formatting so that its more robust, and it will probably move to a different library, so you should 
consider it unstable._

```kotlin
"Hello %1, today is %2".format("World", "Monday") // Returns "Hello World, today is Monday"
"Value: %1, Value2: %2".format(10, "test") // Returns "Value: 10, Value2: test"
```

#### Date & Time Formatting

Date and time formatting are what this library is all about and there are several ways to do it.  

```kotlin
// 1. Basic Usage with Defaults (Short Date and Time) in the local time zone.
val now = Clock.System.now().toLocalDateTime()

val formattedNow = now.format(DateTimeStyle.SHORT, DateTimeStyle.SHORT)
println("Now (Short Date & Time): $formattedNow")
// example output : Now (Short Date & Time): 10/26/24, 8:56 PM

// 2. Medium Date and Time
val mediumFormatted = now.format(DateTimeStyle.MEDIUM, DateTimeStyle.MEDIUM)
println("Now (Medium Date & Time): $mediumFormatted")
// example output : Now (Medium Date & Time): Oct 26, 2024, 8:56:56 PM

// 3. Long Date and Time
val longFormatted = now.format(DateTimeStyle.LONG, DateTimeStyle.LONG)
println("Now (Long Date & Time): $longFormatted")
// example output : Now (Long Date & Time): October 26, 2024 at 8:56:56 PM GMT+1
```

# Sources

https://en.wikipedia.org/wiki/Date_and_time_representation_by_country
https://en.wikipedia.org/wiki/Thai_six-hour_clock
https://en.wikipedia.org/wiki/Italian_six-hour_clock
https://en.wikipedia.org/wiki/Common_Locale_Data_Repository

# Feedback

Bugs can go into the issue tracker, but you are probably going to get faster support by creating a PR.   
