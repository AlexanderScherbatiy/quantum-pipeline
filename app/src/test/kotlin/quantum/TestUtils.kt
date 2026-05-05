package quantum

import org.junit.jupiter.api.Assertions
import quantum.complex.CartesianComplex
import quantum.complex.ComplexExpression
import quantum.math.array.ComplexExpressionVector
import quantum.math.array.ComplexVector
import quantum.math.array.tensor
import quantum.pipeline.ArrayQuantumGate
import quantum.pipeline.QuantumGate
import quantum.pipeline.QuantumStateArray
import quantum.pipeline.QuantumStateExpression
import quantum.pipeline.QuantumStateTensor
import quantum.pipeline.Qubit
import quantum.simple.complex.SimpleComplexCalculator
import quantum.simple.math.ComplexExpressionMatrix
import quantum.simple.math.ComplexMatrix
import quantum.simple.math.SimpleMath

private val DELTA = 0.001

private val complexCalculator = SimpleComplexCalculator()

private val simpleMath = SimpleMath(complexCalculator)

fun assertComplex(value: ComplexExpression, real: Double, image: Double) {
    assertComplex(value.toResult(), real, image)
}

private fun assertComplex(value1: ComplexExpression, value2: ComplexExpression) {
    val c1 = value1.toCartesian()
    val c2 = value2.toCartesian()
    assertComplex(c1, c2.real, c2.image)
}

private fun assertComplex(value: CartesianComplex, real: Double, image: Double) {
    Assertions.assertEquals(real, value.real, DELTA)
    Assertions.assertEquals(image, value.image, DELTA)
}

fun ComplexExpression.toCartesian(): CartesianComplex = when (val c = simpleMath.calc(this)) {
    is CartesianComplex -> c
    else -> throw Error("Unknown complex type: $this")
}

fun ComplexExpression.toResult(): CartesianComplex {
    return complexCalculator.calculate(this).toCartesian()
}

fun assertVector(vector: ComplexVector, expected: ComplexVector) {
    Assertions.assertEquals(vector.size, expected.size)
    for (i in 0 until vector.size) {
        assertComplex(vector[i], expected[i])
    }
}

fun assertMatrix(matrix: ComplexExpressionMatrix, expected: ComplexMatrix) {
    assertMatrix(
        matrix.map { row -> row.map { complexCalculator.calculate(it) }.toTypedArray() }.toTypedArray(), expected
    )
}

fun assertMatrix(matrix: ComplexMatrix, expected: ComplexMatrix) {
    if (matrix.size != expected.size) throw Error("Matrix rows ${matrix.size} != ${expected.size}")
    if (matrix[0].size != expected[0].size) throw Error("Matrix columns ${matrix[0].size} != ${expected[0].size}")

    for (i in matrix.indices) {
        val row1 = matrix[i]
        val row2 = expected[i]
        for (j in row1.indices) {
            if (row1[j] != row2[j]) throw Error("Matrix elem[$i][$j] ${row1[j]} != ${row2[j]}")
        }
    }
}

fun QuantumStateExpression.toResult(): Array<CartesianComplex> = when (this) {
    is Qubit -> arrayOf(this.c1.toResult(), this.c2.toResult())
    is QuantumStateArray -> this.values.map { it.toResult() }.toTypedArray()
    is QuantumStateTensor -> {
        val v1 = this.state1.toResult() as ComplexExpressionVector
        val v2 = this.state2.toResult() as ComplexExpressionVector
        v1.tensor(v2, simpleMath.complexCalc).map { it.toResult() }.toTypedArray()
    }
    else -> throw Error("Unknown quantum state type: $this")
}

fun assertQuantumState(state: QuantumStateExpression, expected: ComplexExpressionVector) {
    val values = state.toResult()
    Assertions.assertEquals(expected.size, values.size)
    for (i in 0..<values.size) {
        assertComplex(values[i], expected[i])
    }
}

fun assertQuantumGate(gate: QuantumGate, matrix: ComplexExpressionMatrix) {
    val values = gate.toMatrix()

    matrix.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, value ->
            assertComplex(value, values[rowIndex][columnIndex])
        }
    }
}

private fun QuantumGate.toMatrix(): Array<Array<CartesianComplex>> = when (this) {
    is ArrayQuantumGate -> this.data.map { row ->
            row.map { elem -> calc(elem) }.toTypedArray()
        }.toTypedArray()

    else -> throw Error("Unknown gate type: $this")
}

private fun calc(c: ComplexExpression): CartesianComplex = complexCalculator.calculate(c).toResult()

fun assertComplexVector(expected: ComplexVector, vector: ComplexVector) {
    Assertions.assertEquals(expected.size, vector.size)
    for (i in 0..<expected.size) {
        assertComplex(expected[i], vector[i])
    }
}