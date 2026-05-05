package quantum.simple.pipeline

import quantum.complex.C0
import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.math.array.ComplexExpressionVector
import quantum.math.array.ComplexVector
import quantum.math.array.tensor
import quantum.pipeline.ArrayQuantumGate
import quantum.pipeline.QuantumBlock
import quantum.pipeline.QuantumBlockElem
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
        is QuantumStateTensor ->
            (this.toVector(state.state1) as ComplexExpressionVector).tensor(
                this.toVector(state.state2) as ComplexExpressionVector, this.math.complexCalc)
        else -> throw Error("Unknown quantum state: ${state}")
    }
}


private fun calc(math: SimpleMath, state: ComplexVector, gate: QuantumGate): ComplexVector {
    // TBD
    // return math.mul(state, toMatrix(gate))
    return math.mul(state.map { it as Complex }.toTypedArray(), toMatrix(math, gate))
}

private fun toMatrix(math: SimpleMath, gate: QuantumGate): ComplexExpressionMatrix = when (gate) {
    is ArrayQuantumGate -> gate.data
    is QuantumBlock -> quantumBlockToMatrix(math, gate)
    else -> throw Error("Unknown quantum gate: ${gate}")
}

///*
private fun quantumBlockToMatrix(math: SimpleMath, block: QuantumBlock): ComplexExpressionMatrix {
    return block.elems
        .map({ quantumBlockElemToMatrix(block.size, it) })
        .reduce { acc, matrix -> math.mul(acc, matrix) }
}

private fun quantumBlockElemToMatrix(size: Int, elem: QuantumBlockElem): ComplexExpressionMatrix {

    val matrixSize = 2 shl size
    val matrix = Array(matrixSize) { Array(matrixSize) { C0 as ComplexExpression } }

    return matrix
}
//*/
