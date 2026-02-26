package quantum.simple.pipeline

import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.pipeline.ArrayQuantumGate
import quantum.pipeline.QuantumCalculator
import quantum.pipeline.QuantumGate
import quantum.pipeline.QuantumPipeline
import quantum.pipeline.QuantumState
import quantum.pipeline.Qubit
import quantum.simple.complex.SimpleComplexCalculator
import quantum.simple.math.SimpleMath

class SimpleQuantumCalculator : QuantumCalculator {

    private val math: SimpleMath

    constructor(complexCalc: ComplexCalculator = SimpleComplexCalculator()) {
        this.math = SimpleMath(complexCalc)
    }

    override fun calculate(pipeline: QuantumPipeline): QuantumState {
        var result = toVector(pipeline.state)
        for (gate in pipeline.gates) {
            result = calc(math, result, gate)
        }
        return Qubit(result[0], result[1])
    }
}

private fun toVector(state: QuantumState): Array<Complex> = when (state) {
    is Qubit -> arrayOf(state.c1, state.c2)
    else -> throw Error("Unknown quantum state: ${state}")
}

private fun calc(math: SimpleMath, state: Array<Complex>, gate: QuantumGate): Array<Complex> {
    return math.mul(state, toMatrix(gate))
}

private fun toMatrix(gate: QuantumGate): Array<Array<ComplexExpression>> = when (gate) {
    is ArrayQuantumGate -> gate.data
    else -> throw Error("Unknown quantum gate: ${gate}")
}
