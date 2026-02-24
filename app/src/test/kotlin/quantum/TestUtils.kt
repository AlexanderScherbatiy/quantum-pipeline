package quantum

import org.junit.jupiter.api.Assertions
import quantum.complex.CartesianComplex
import quantum.complex.Complex
import quantum.complex.ComplexExpression
import quantum.simple.complex.SimpleComplexCalculator

private val DELTA = 0.001

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

fun Complex.toResult() = when (this) {
    is CartesianComplex -> ComplexResult(this.real, this.image)
    else -> throw Error("Unknown complex type: $this")
}

data class ComplexResult(val real: Double, val image: Double)

fun assertQuantumGate(expected: QuntumGate, matrix: Array<Array<ComplexResult>>) {
    val res = expected.toResult()

    matrix.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, value ->
            assertComplex(value, res.values[rowIndex][columnIndex])
        }
    }
}

private fun QuntumGate.toResult(): QuantumGateResult = when (this) {
    is ArrayQuantumGate -> QuantumGateResult(this.data.map({ row -> row.map({ elem -> calc(elem) }) }))
    else -> throw Error("Unknown gate type: $this")
}

private data class QuantumGateResult(val values: List<List<ComplexResult>>)

private val complexCalculator = SimpleComplexCalculator()
private fun calc(c: ComplexExpression): ComplexResult = complexCalculator.calculate(c).toResult()
