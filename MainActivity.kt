package com.sen204.calculatorapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity_Lifecycle"
    }

    // ---------- Mode layouts ----------
    private lateinit var spinnerMode: Spinner
    private lateinit var layoutBasic: LinearLayout
    private lateinit var layoutScientific: LinearLayout
    private lateinit var layoutMatrix: LinearLayout
    private lateinit var layoutCombinatorics: LinearLayout
    private lateinit var layoutStatistics: LinearLayout

    // ---------- Basic mode views ----------
    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button

    // ---------- Scientific mode views ----------
    private lateinit var etSciInput: EditText
    private lateinit var btnSin: Button
    private lateinit var btnCos: Button
    private lateinit var btnTan: Button
    private lateinit var btnSinh: Button
    private lateinit var btnCosh: Button
    private lateinit var btnTanh: Button
    private lateinit var btnSqrt: Button
    private lateinit var btnSquare: Button
    private lateinit var btnLog: Button
    private lateinit var btnLn: Button
    private lateinit var btnFactorial: Button
    private lateinit var btnExp: Button
    private lateinit var btnInverse: Button

    // ---------- Matrix mode views ----------
    private lateinit var spinnerMatrixSize: Spinner
    private lateinit var etMatrixA: EditText
    private lateinit var etMatrixB: EditText
    private lateinit var btnMatrixAdd: Button
    private lateinit var btnMatrixSubtract: Button
    private lateinit var btnMatrixMultiply: Button
    private lateinit var btnMatrixDeterminant: Button

    // ---------- Combinatorics mode views ----------
    private lateinit var etN: EditText
    private lateinit var etR: EditText
    private lateinit var btnPermutation: Button
    private lateinit var btnCombination: Button

    // ---------- Statistics mode views ----------
    private lateinit var etStatsData: EditText
    private lateinit var btnMean: Button
    private lateinit var btnMedian: Button
    private lateinit var btnMode: Button
    private lateinit var btnStdDev: Button
    private lateinit var btnVariance: Button

    // ---------- Shared ----------
    private lateinit var tvResult: TextView
    private lateinit var btnClear: Button

    private val modeNames = arrayOf("Basic", "Scientific", "Matrix", "Combinatorics", "Statistics")
    private val matrixSizeNames = arrayOf("2x2", "3x3", "4x4")

    // =====================================================================
    // LIFECYCLE CALLBACKS
    // =====================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        // Call the superclass implementation first
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called - Activity is being created")

        // Bind the Kotlin file to the XML UI layout
        setContentView(R.layout.activity_main)

        // Your custom initialization goes here
        initViews()
        setupModeSpinner()
        setupMatrixSizeSpinner()
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called - Activity is becoming visible")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called - Activity is in the foreground, user can interact")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called - Activity losing focus, save draft data here")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called - Activity is no longer visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called - Activity is being destroyed")
    }

    // =====================================================================
    // VIEW SETUP
    // =====================================================================

    private fun initViews() {
        // Mode layouts
        spinnerMode = findViewById(R.id.spinnerMode)
        layoutBasic = findViewById(R.id.layoutBasic)
        layoutScientific = findViewById(R.id.layoutScientific)
        layoutMatrix = findViewById(R.id.layoutMatrix)
        layoutCombinatorics = findViewById(R.id.layoutCombinatorics)
        layoutStatistics = findViewById(R.id.layoutStatistics)

        // Basic
        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)

        // Scientific
        etSciInput = findViewById(R.id.etSciInput)
        btnSin = findViewById(R.id.btnSin)
        btnCos = findViewById(R.id.btnCos)
        btnTan = findViewById(R.id.btnTan)
        btnSinh = findViewById(R.id.btnSinh)
        btnCosh = findViewById(R.id.btnCosh)
        btnTanh = findViewById(R.id.btnTanh)
        btnSqrt = findViewById(R.id.btnSqrt)
        btnSquare = findViewById(R.id.btnSquare)
        btnLog = findViewById(R.id.btnLog)
        btnLn = findViewById(R.id.btnLn)
        btnFactorial = findViewById(R.id.btnFactorial)
        btnExp = findViewById(R.id.btnExp)
        btnInverse = findViewById(R.id.btnInverse)

        // Matrix
        spinnerMatrixSize = findViewById(R.id.spinnerMatrixSize)
        etMatrixA = findViewById(R.id.etMatrixA)
        etMatrixB = findViewById(R.id.etMatrixB)
        btnMatrixAdd = findViewById(R.id.btnMatrixAdd)
        btnMatrixSubtract = findViewById(R.id.btnMatrixSubtract)
        btnMatrixMultiply = findViewById(R.id.btnMatrixMultiply)
        btnMatrixDeterminant = findViewById(R.id.btnMatrixDeterminant)

        // Combinatorics
        etN = findViewById(R.id.etN)
        etR = findViewById(R.id.etR)
        btnPermutation = findViewById(R.id.btnPermutation)
        btnCombination = findViewById(R.id.btnCombination)

        // Statistics
        etStatsData = findViewById(R.id.etStatsData)
        btnMean = findViewById(R.id.btnMean)
        btnMedian = findViewById(R.id.btnMedian)
        btnMode = findViewById(R.id.btnMode)
        btnStdDev = findViewById(R.id.btnStdDev)
        btnVariance = findViewById(R.id.btnVariance)

        // Shared
        tvResult = findViewById(R.id.tvResult)
        btnClear = findViewById(R.id.btnClear)
    }

    private fun setupModeSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, modeNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMode.adapter = adapter

        spinnerMode.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: android.widget.AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                showModeLayout(position)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun setupMatrixSizeSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, matrixSizeNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMatrixSize.adapter = adapter
    }

    private fun showModeLayout(position: Int) {
        layoutBasic.visibility = if (position == 0) View.VISIBLE else View.GONE
        layoutScientific.visibility = if (position == 1) View.VISIBLE else View.GONE
        layoutMatrix.visibility = if (position == 2) View.VISIBLE else View.GONE
        layoutCombinatorics.visibility = if (position == 3) View.VISIBLE else View.GONE
        layoutStatistics.visibility = if (position == 4) View.VISIBLE else View.GONE
        tvResult.text = "Result: 0"
    }

    // =====================================================================
    // CLICK LISTENERS
    // =====================================================================

    private fun setupClickListeners() {

        // ---------------- BASIC ----------------
        btnAdd.setOnClickListener {
            val num1 = getDoubleFromField(etNum1)
            val num2 = getDoubleFromField(etNum2)
            if (num1 != null && num2 != null) {
                val sum = num1 + num2
                tvResult.text = "Result: " + formatNumber(sum)
            }
        }

        btnSubtract.setOnClickListener {
            val num1 = getDoubleFromField(etNum1)
            val num2 = getDoubleFromField(etNum2)
            if (num1 != null && num2 != null) {
                val difference = num1 - num2
                tvResult.text = "Result: " + formatNumber(difference)
            }
        }

        btnMultiply.setOnClickListener {
            val num1 = getDoubleFromField(etNum1)
            val num2 = getDoubleFromField(etNum2)
            if (num1 != null && num2 != null) {
                val product = num1 * num2
                tvResult.text = "Result: " + formatNumber(product)
            }
        }

        btnDivide.setOnClickListener {
            val num1 = getDoubleFromField(etNum1)
            val num2 = getDoubleFromField(etNum2)
            if (num1 != null && num2 != null) {
                if (num2 == 0.0) {
                    showError("Cannot divide by zero")
                } else {
                    val quotient = num1 / num2
                    tvResult.text = "Result: " + formatNumber(quotient)
                }
            }
        }

        // ---------------- SCIENTIFIC ----------------
        btnSin.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                val radians = Math.toRadians(x)
                tvResult.text = "Result: " + formatNumber(sin(radians))
            }
        }

        btnCos.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                val radians = Math.toRadians(x)
                tvResult.text = "Result: " + formatNumber(cos(radians))
            }
        }

        btnTan.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                val radians = Math.toRadians(x)
                tvResult.text = "Result: " + formatNumber(tan(radians))
            }
        }

        btnSinh.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                tvResult.text = "Result: " + formatNumber(sinh(x))
            }
        }

        btnCosh.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                tvResult.text = "Result: " + formatNumber(cosh(x))
            }
        }

        btnTanh.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                tvResult.text = "Result: " + formatNumber(tanh(x))
            }
        }

        btnSqrt.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                if (x < 0) {
                    showError("Cannot take square root of a negative number")
                } else {
                    tvResult.text = "Result: " + formatNumber(sqrt(x))
                }
            }
        }

        btnSquare.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                tvResult.text = "Result: " + formatNumber(x * x)
            }
        }

        btnLog.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                if (x <= 0) {
                    showError("Logarithm undefined for values <= 0")
                } else {
                    tvResult.text = "Result: " + formatNumber(log10(x))
                }
            }
        }

        btnLn.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                if (x <= 0) {
                    showError("Natural log undefined for values <= 0")
                } else {
                    tvResult.text = "Result: " + formatNumber(ln(x))
                }
            }
        }

        btnFactorial.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                if (x < 0 || x != floor(x)) {
                    showError("Factorial requires a non-negative whole number")
                } else if (x > 170) {
                    showError("Number too large for factorial")
                } else {
                    val result = factorial(x.toLong())
                    tvResult.text = "Result: " + formatNumber(result)
                }
            }
        }

        btnExp.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                tvResult.text = "Result: " + formatNumber(exp(x))
            }
        }

        btnInverse.setOnClickListener {
            val x = getDoubleFromField(etSciInput)
            if (x != null) {
                if (x == 0.0) {
                    showError("Cannot divide by zero")
                } else {
                    tvResult.text = "Result: " + formatNumber(1.0 / x)
                }
            }
        }

        // ---------------- MATRIX ----------------
        btnMatrixAdd.setOnClickListener { handleMatrixOperation("add") }
        btnMatrixSubtract.setOnClickListener { handleMatrixOperation("subtract") }
        btnMatrixMultiply.setOnClickListener { handleMatrixOperation("multiply") }
        btnMatrixDeterminant.setOnClickListener { handleMatrixDeterminant() }

        // ---------------- COMBINATORICS ----------------
        btnPermutation.setOnClickListener {
            val n = getIntFromField(etN)
            val r = getIntFromField(etR)
            if (n != null && r != null) {
                if (r > n || n < 0 || r < 0) {
                    showError("Require n >= r >= 0")
                } else {
                    val result = permutation(n, r)
                    tvResult.text = "Result: " + formatNumber(result)
                }
            }
        }

        btnCombination.setOnClickListener {
            val n = getIntFromField(etN)
            val r = getIntFromField(etR)
            if (n != null && r != null) {
                if (r > n || n < 0 || r < 0) {
                    showError("Require n >= r >= 0")
                } else {
                    val result = combination(n, r)
                    tvResult.text = "Result: " + formatNumber(result)
                }
            }
        }

        // ---------------- STATISTICS ----------------
        btnMean.setOnClickListener {
            val data = getStatsData()
            if (data != null) {
                tvResult.text = "Result: " + formatNumber(calculateMean(data))
            }
        }

        btnMedian.setOnClickListener {
            val data = getStatsData()
            if (data != null) {
                tvResult.text = "Result: " + formatNumber(calculateMedian(data))
            }
        }

        btnMode.setOnClickListener {
            val data = getStatsData()
            if (data != null) {
                tvResult.text = "Result: " + calculateMode(data)
            }
        }

        btnStdDev.setOnClickListener {
            val data = getStatsData()
            if (data != null) {
                if (data.size < 2) {
                    showError("Need at least 2 values")
                } else {
                    tvResult.text = "Result: " + formatNumber(calculateStdDev(data))
                }
            }
        }

        btnVariance.setOnClickListener {
            val data = getStatsData()
            if (data != null) {
                if (data.size < 2) {
                    showError("Need at least 2 values")
                } else {
                    tvResult.text = "Result: " + formatNumber(calculateVariance(data))
                }
            }
        }

        // ---------------- CLEAR ----------------
        btnClear.setOnClickListener {
            clearAllFields()
        }
    }

    // =====================================================================
    // INPUT HELPERS (Error Prevention - check for empty fields, per slides)
    // =====================================================================

    private fun getDoubleFromField(field: EditText): Double? {
        val text = field.text.toString()
        if (text.isEmpty()) {
            showError("Please fill in all required fields")
            return null
        }
        return try {
            text.toDouble()
        } catch (e: NumberFormatException) {
            showError("Please enter a valid number")
            null
        }
    }

    private fun getIntFromField(field: EditText): Int? {
        val text = field.text.toString()
        if (text.isEmpty()) {
            showError("Please fill in all required fields")
            return null
        }
        return try {
            text.toInt()
        } catch (e: NumberFormatException) {
            showError("Please enter a valid whole number")
            null
        }
    }

    private fun getStatsData(): DoubleArray? {
        val text = etStatsData.text.toString()
        if (text.isEmpty()) {
            showError("Please enter at least one number")
            return null
        }
        return try {
            val parts = text.split(",")
            val result = DoubleArray(parts.size)
            for (i in parts.indices) {
                result[i] = parts[i].trim().toDouble()
            }
            result
        } catch (e: NumberFormatException) {
            showError("Please enter valid comma-separated numbers")
            null
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun formatNumber(value: Double): String {
        // Trim trailing .0 for whole numbers, otherwise show up to 6 decimal places
        return if (value == floor(value) && !value.isInfinite() && abs(value) < 1e15) {
            value.toLong().toString()
        } else {
            String.format("%.6f", value).trimEnd('0').trimEnd('.')
        }
    }

    private fun clearAllFields() {
        etNum1.text.clear()
        etNum2.text.clear()
        etSciInput.text.clear()
        etMatrixA.text.clear()
        etMatrixB.text.clear()
        etN.text.clear()
        etR.text.clear()
        etStatsData.text.clear()
        tvResult.text = "Result: 0"
    }

    // =====================================================================
    // MATRIX LOGIC
    // =====================================================================

    private fun parseMatrix(text: String): Array<DoubleArray>? {
        return try {
            val rows = text.trim().split(";")
            val matrix = Array(rows.size) { rowIndex ->
                val values = rows[rowIndex].trim().split(",")
                DoubleArray(values.size) { colIndex -> values[colIndex].trim().toDouble() }
            }
            // Validate all rows have the same length
            val firstRowLength = matrix[0].size
            for (row in matrix) {
                if (row.size != firstRowLength) {
                    return null
                }
            }
            matrix
        } catch (e: Exception) {
            null
        }
    }

    private fun matrixToString(matrix: Array<DoubleArray>): String {
        val builder = StringBuilder()
        for (row in matrix) {
            val rowStrings = row.map { formatNumber(it) }
            builder.append("[" + rowStrings.joinToString(", ") + "]\n")
        }
        return builder.toString().trim()
    }

    private fun handleMatrixOperation(operation: String) {
        val matrixA = parseMatrix(etMatrixA.text.toString())
        val matrixB = parseMatrix(etMatrixB.text.toString())

        if (matrixA == null || matrixB == null) {
            showError("Invalid matrix format. Use rows separated by ';' and values by ','")
            return
        }

        if (matrixA.size != matrixB.size || matrixA[0].size != matrixB[0].size) {
            if (operation == "add" || operation == "subtract") {
                showError("Matrices must be the same size to add or subtract")
                return
            }
        }

        when (operation) {
            "add" -> {
                val result = Array(matrixA.size) { i -> DoubleArray(matrixA[0].size) { j -> matrixA[i][j] + matrixB[i][j] } }
                tvResult.text = "Result:\n" + matrixToString(result)
            }
            "subtract" -> {
                val result = Array(matrixA.size) { i -> DoubleArray(matrixA[0].size) { j -> matrixA[i][j] - matrixB[i][j] } }
                tvResult.text = "Result:\n" + matrixToString(result)
            }
            "multiply" -> {
                if (matrixA[0].size != matrixB.size) {
                    showError("Columns of A must match rows of B for multiplication")
                    return
                }
                val result = Array(matrixA.size) { DoubleArray(matrixB[0].size) }
                for (i in matrixA.indices) {
                    for (j in matrixB[0].indices) {
                        var sum = 0.0
                        for (k in matrixB.indices) {
                            sum += matrixA[i][k] * matrixB[k][j]
                        }
                        result[i][j] = sum
                    }
                }
                tvResult.text = "Result:\n" + matrixToString(result)
            }
        }
    }

    private fun handleMatrixDeterminant() {
        val matrixA = parseMatrix(etMatrixA.text.toString())
        if (matrixA == null) {
            showError("Invalid matrix format for Matrix A")
            return
        }
        if (matrixA.size != matrixA[0].size) {
            showError("Determinant requires a square matrix")
            return
        }
        if (matrixA.size > 4) {
            showError("Only up to 4x4 matrices are supported")
            return
        }
        val det = calculateDeterminant(matrixA)
        tvResult.text = "Result: " + formatNumber(det)
    }

    // Recursive cofactor expansion - supports 2x2 up to 4x4
    private fun calculateDeterminant(matrix: Array<DoubleArray>): Double {
        val n = matrix.size

        if (n == 1) {
            return matrix[0][0]
        }
        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
        }

        var determinant = 0.0
        for (col in 0 until n) {
            val minor = getMinor(matrix, 0, col)
            val sign = if (col % 2 == 0) 1.0 else -1.0
            determinant += sign * matrix[0][col] * calculateDeterminant(minor)
        }
        return determinant
    }

    private fun getMinor(matrix: Array<DoubleArray>, excludeRow: Int, excludeCol: Int): Array<DoubleArray> {
        val n = matrix.size
        val minor = Array(n - 1) { DoubleArray(n - 1) }
        var minorRow = 0
        for (row in 0 until n) {
            if (row == excludeRow) continue
            var minorCol = 0
            for (col in 0 until n) {
                if (col == excludeCol) continue
                minor[minorRow][minorCol] = matrix[row][col]
                minorCol++
            }
            minorRow++
        }
        return minor
    }

    // =====================================================================
    // COMBINATORICS LOGIC
    // =====================================================================

    private fun factorial(n: Long): Double {
        var result = 1.0
        for (i in 2..n) {
            result *= i
        }
        return result
    }

    private fun permutation(n: Int, r: Int): Double {
        // nPr = n! / (n - r)!
        var result = 1.0
        for (i in 0 until r) {
            result *= (n - i)
        }
        return result
    }

    private fun combination(n: Int, r: Int): Double {
        // nCr = nPr / r!
        val perm = permutation(n, r)
        return perm / factorial(r.toLong())
    }

    // =====================================================================
    // STATISTICS LOGIC
    // =====================================================================

    private fun calculateMean(data: DoubleArray): Double {
        var sum = 0.0
        for (value in data) {
            sum += value
        }
        return sum / data.size
    }

    private fun calculateMedian(data: DoubleArray): Double {
        val sorted = data.sortedArray()
        val middle = sorted.size / 2
        return if (sorted.size % 2 == 0) {
            (sorted[middle - 1] + sorted[middle]) / 2.0
        } else {
            sorted[middle]
        }
    }

    private fun calculateMode(data: DoubleArray): String {
        val frequencyMap = HashMap<Double, Int>()
        for (value in data) {
            val currentCount = frequencyMap[value] ?: 0
            frequencyMap[value] = currentCount + 1
        }

        var maxFrequency = 0
        for (count in frequencyMap.values) {
            if (count > maxFrequency) {
                maxFrequency = count
            }
        }

        if (maxFrequency == 1) {
            return "No mode (all values unique)"
        }

        val modes = ArrayList<String>()
        for (entry in frequencyMap.entries) {
            if (entry.value == maxFrequency) {
                modes.add(formatNumber(entry.key))
            }
        }
        return modes.joinToString(", ")
    }

    private fun calculateVariance(data: DoubleArray): Double {
        val mean = calculateMean(data)
        var sumSquaredDiff = 0.0
        for (value in data) {
            val diff = value - mean
            sumSquaredDiff += diff * diff
        }
        // Sample variance: divide by (n - 1)
        return sumSquaredDiff / (data.size - 1)
    }

    private fun calculateStdDev(data: DoubleArray): Double {
        return sqrt(calculateVariance(data))
    }
}
