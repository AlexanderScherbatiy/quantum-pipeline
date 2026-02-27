package quantum

import org.junit.jupiter.api.Assertions
import quantum.complex.CartesianComplex
import quantum.complex.Complex
import quantum.complex.ComplexExpression
import quantum.pipeline.ArrayQuantumGate
import quantum.pipeline.QuantumGate
import quantum.pipeline.QuantumStateArray
import quantum.pipeline.QuantumStateExpression
import quantum.pipeline.QuantumStateTensor
import quantum.pipeline.Qubit
import quantum.simple.complex.SimpleComplexCalculator
import quantum.simple.math.SimpleMath

private val DELTA = 0.001

private val complexCalculator = SimpleComplexCalculator()

private val simpleMath = SimpleMath(complexCalculator)

fun assertComplex(value: Complex, real: Double, image: Double) {
    assertComplex(value.toResult(), real, image)
}

private fun assertComplex(value1: ComplexResult, value2: ComplexResult) {
    assertComplex(value1, value2.real, value2.image)
}

private fun assertComplex(value: ComplexResult, real: Double, image: Double) {
    Assertions.assertEquals(real, value.real, DELTA)
    Assertions.assertEquals(image, value.image, DELTA)
}

fun Complex.toResult(): ComplexResult = when (this) {
    is CartesianComplex -> ComplexResult(this.real, this.image)
    else -> throw Error("Unknown complex type: $this")
}

fun ComplexExpression.toResult(): ComplexResult {
    return complexCalculator.calculate(this).toResult()
}

fun assertVector(vector: Array<Complex>, expected: Array<ComplexResult>) {
    assertVector(vector.map({ it.toResult() }).toTypedArray(), expected)
}

fun assertVector(vector: Array<ComplexResult>, expected: Array<ComplexResult>) {
    Assertions.assertEquals(vector.size, expected.size)
    for (i in 0 until vector.size) {
        assertComplex(vector[i], expected[i])
    }
}

fun QuantumStateExpression.toResult(): QuantumStateResult = when (this) {
    is Qubit -> QuantumStateResult(arrayOf(this.c1.toResult(), this.c2.toResult()))
    is QuantumStateArray -> QuantumStateResult(this.values.map { it.toResult() }.toTypedArray())
    is QuantumStateTensor -> {
        val values = this.states
            .map {
                val stateResult = it.toResult()
                stateResult.values
                    .map { CartesianComplex(it.real, it.image) as Complex }.toTypedArray()
            }
            .toTypedArray()
        val results = simpleMath.tensor(values).map { it.toResult() }.toTypedArray()
        QuantumStateResult(results)
    }

    else -> throw Error("Unknown quantum state type: $this")
}

fun assertQuantumState(state: QuantumStateExpression, expected: Array<ComplexExpression>) {
    assertQuantumState(state, expected.map { it.toResult() }.toTypedArray())
}

fun assertQuantumState(state: QuantumStateExpression, expected: Array<ComplexResult>) {
    val result = state.toResult()
    val values = result.values
    Assertions.assertEquals(expected.size, values.size)
    for (i in 0..<values.size) {
        assertComplex(values[i], expected[i])
    }
}

fun assertQuantumGate(gate: QuantumGate, matrix: Array<Array<ComplexResult>>) {
    val res = gate.toResult()

    matrix.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, value ->
            assertComplex(value, res.values[rowIndex][columnIndex])
        }
    }
}

private fun QuantumGate.toResult(): QuantumGateResult = when (this) {
    is ArrayQuantumGate -> QuantumGateResult(this.data.map({ row -> row.map({ elem -> calc(elem) }) }))
    else -> throw Error("Unknown gate type: $this")
}

private data class QuantumGateResult(val values: List<List<ComplexResult>>)

private fun calc(c: ComplexExpression): ComplexResult = complexCalculator.calculate(c).toResult()

// TBD: Use CartesianComplex
data class ComplexResult(val real: Double, val image: Double)

data class QuantumStateResult(val values: Array<ComplexResult>)
