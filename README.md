OLUBIYI BLESSED SEN 102 assignment
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

Thank you
