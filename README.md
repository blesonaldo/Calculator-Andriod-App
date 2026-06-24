# Scientific Calculator App

SEN 104 / SEN 214 — Android App Development
Lab Assignment: Calculator App (Basic + Bonus Scientific Features)

## How to Open This Project

1. Open **Android Studio**.
2. Choose **Open** (not "New Project") and select this `CalculatorApp` folder.
3. Android Studio will detect it's missing the Gradle wrapper jar — when prompted,
   click **"OK" / "Sync Now"**, or go to **File → Sync Project with Gradle Files**
   (the elephant icon). This regenerates the wrapper automatically using the
   `gradle-wrapper.properties` already included.
4. Wait for the Gradle sync to finish (green checkmark, bottom right).
5. Select a device (emulator or USB-connected phone) from the dropdown.
6. Click the green **Run** button.

This matches the exact workflow from the course slides: Project Wizard →
Gradle Sync → Run.

## Project Structure

- `app/src/main/AndroidManifest.xml` — the app's "ID card," declares MainActivity
  as the launcher Activity.
- `app/src/main/res/layout/activity_main.xml` — single XML layout containing all
  five calculator modes, shown/hidden based on the mode Spinner selection.
- `app/src/main/java/com/sen204/calculatorapp/MainActivity.kt` — all app logic.
- `app/src/main/res/values/` — strings, colors, and theme.

## Language Note

Written in **Kotlin**, but deliberately in an explicit, Java-mirroring style to
match what's taught in the slides: `findViewById()` for every view (no view
binding), explicit `setOnClickListener` blocks, manual string-to-number parsing
via `.toDouble()` / `.toInt()` (the Kotlin equivalent of `Double.parseDouble()`),
and the same `onCreate()` → `setContentView()` flow.

## Features

### Basic Mode (core assignment requirement)
- Addition, Subtraction, Multiplication, Division
- Empty-field and divide-by-zero error checking (Toast messages), matching the
  slides' guidance on preventing crashes from bad input

### Scientific Mode (bonus)
- Trigonometric: sin, cos, tan — **input in degrees**, converted internally with
  `Math.toRadians()`
- Hyperbolic: sinh, cosh, tanh
- Other: square root, square, log (base 10), ln (natural log), factorial,
  eˣ, 1/x — each with domain-error checking (e.g. no sqrt of a negative number)

### Matrix Mode (bonus)
- Supports 2×2 up to 4×4 matrices
- Input format: rows separated by `;`, values separated by `,`
  (e.g. `1,2;3,4` for a 2×2 matrix)
- Operations: Add, Subtract, Multiply, Determinant
- Determinant uses recursive cofactor expansion, so it correctly handles sizes
  up to 4×4, not just the simple 2×2 formula

### Combinatorics Mode (bonus)
- nPr (permutations) and nCr (combinations) for any valid n and r

### Statistics Mode (bonus)
- Input: comma-separated list of numbers
- Mean, Median, Mode, Sample Standard Deviation, Sample Variance

## Lifecycle Logging

Every lifecycle callback covered in the slides is overridden in `MainActivity.kt`
with a `Log.d()` call under the tag `MainActivity_Lifecycle`:

- `onCreate()`
- `onStart()`
- `onResume()`
- `onPause()`
- `onStop()`
- `onDestroy()`

To see this in action: run the app, open **Logcat** in Android Studio, filter by
`MainActivity_Lifecycle`, then rotate the emulator/device or press Home — you'll
see the callbacks fire in real time.

## Testing the App

- **Basic**: type `15` and `25`, tap `+` → should show `40`.
- **Scientific**: enter `30`, tap `sin` → should show `0.5` (sin of 30 degrees).
- **Matrix**: Matrix A = `1,2;3,4`, Matrix B = `5,6;7,8`, tap `A+B` → should show
  `[6, 8]` / `[10, 12]`.
- **Combinatorics**: n = `5`, r = `2`, tap `nCr` → should show `10`.
- **Statistics**: data = `4,8,6,5,3,9`, tap `Mean` → should show `5.833333`.
