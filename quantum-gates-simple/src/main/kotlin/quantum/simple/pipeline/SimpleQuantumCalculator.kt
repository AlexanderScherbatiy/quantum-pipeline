package quantum.simple.pipeline

import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexZero
import quantum.complex.mul
import quantum.complex.sum
import quantum.pipeline.ArrayQuantumGate
import quantum.pipeline.QuantumCalculator
import quantum.pipeline.QuantumGate
import quantum.pipeline.QuantumPipeline
import quantum.pipeline.QuantumState
import quantum.pipeline.Qubit
import quantum.simple.complex.SimpleComplexCalculator

class SimpleQuantumCalculator : QuantumCalculator {

    private val complexCalc: ComplexCalculator

    constructor(complexCalc: ComplexCalculator = SimpleComplexCalculator()) {
        this.complexCalc = complexCalc
    }

    override fun calculate(pipeline: QuantumPipeline): QuantumState {
        var result = calc(pipeline.state)
        for (gate in pipeline.gates) {
            result = calc(complexCalc, result, gate)
        }
        return Qubit(result[0], result[1])
    }
}

private fun calc(state: QuantumState): Array<Complex> = when (state) {
    is Qubit -> arrayOf(state.c1, state.c2)
    else -> throw Error("Unknown quantum state: ${state}")
}

private fun calc(calc: ComplexCalculator, gate: QuantumGate): Array<Array<Complex>> = when (gate) {
    is ArrayQuantumGate -> gate.data
        .map({ row ->
            row
                .map({ column -> calc.calculate(column) }).toTypedArray()
        }).toTypedArray()

    else -> throw Error("Unknown quantum gate: ${gate}")
}

private fun calc(calc: ComplexCalculator, state: Array<Complex>, gate: QuantumGate): Array<Complex> {
    val matrix = calc(calc, gate)
    return matrix.map({ row ->
        var c: Complex = ComplexZero
        for (i in 0 until row.size) {
            val s = state[i]
            val r = row[i]
            val expr = c.sum(s.mul(r))
            c = calc.calculate(expr)
        }
        c
    }).toTypedArray()
}


