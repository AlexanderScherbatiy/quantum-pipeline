package quantum.simple.pipeline

import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.pipeline.ArrayQuantumGate
import quantum.pipeline.QuantumCalculator
import quantum.pipeline.QuantumGate
import quantum.pipeline.QuantumPipeline
import quantum.pipeline.QuantumState
import quantum.pipeline.QuantumStateArray
import quantum.pipeline.QuantumStateExpression
import quantum.pipeline.QuantumStateTensor
import quantum.pipeline.Qubit
import quantum.simple.complex.SimpleComplexCalculator
import quantum.simple.math.ComplexExpressionMatrix
import quantum.simple.math.ComplexVector
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
                .map({ it as Complex })
                .toTypedArray()
        }
        if (result.size == 2) return Qubit(result[0], result[1])
        // TBD:
        return QuantumStateArray(result.map { it as ComplexExpression }.toTypedArray())
    }

    private fun toVector(state: QuantumStateExpression): ComplexVector = when (state) {
        is Qubit -> arrayOf(math.calc(state.c1), math.calc(state.c2))
        is QuantumStateTensor -> math.tensor(state.states.map { this.toVector(it) }.toTypedArray())
        else -> throw Error("Unknown quantum state: ${state}")
    }
}


private fun calc(math: SimpleMath, state: ComplexVector, gate: QuantumGate): ComplexVector {
    // TBD
    // return math.mul(state, toMatrix(gate))
    return math.mul(state.map { it as Complex }.toTypedArray(), toMatrix(gate))
}

private fun toMatrix(gate: QuantumGate): ComplexExpressionMatrix = when (gate) {
    is ArrayQuantumGate -> gate.data
    // is QuantumBlock -> // TBD
    else -> throw Error("Unknown quantum gate: ${gate}")
}
