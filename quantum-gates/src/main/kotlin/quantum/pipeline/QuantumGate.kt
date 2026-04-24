package quantum.pipeline

import quantum.complex.C0
import quantum.complex.C1
import quantum.complex.CI
import quantum.complex.unaryMinus
import quantum.complex.ComplexExpression

interface QuantumGate

data class ArrayQuantumGate(val data: Array<Array<ComplexExpression>>) : QuantumGate

// 0 1
// 1 0
val QuantumGateX = ArrayQuantumGate(
    arrayOf(
        arrayOf(C0, C1),
        arrayOf(C1, C0),
    )
)

// 0 1
// 1 0
val QuantumGateY = ArrayQuantumGate(
    arrayOf(
        arrayOf(C0, -CI),
        arrayOf(CI, C0),
    )
)

// 1 0 0 0
// 0 1 0 0
// 0 0 0 1
// 0 0 1 0
val QuantumGateControlledNot = ArrayQuantumGate(
    arrayOf(
        arrayOf(C1, C0, C0, C0),
        arrayOf(C0, C1, C0, C0),
        arrayOf(C0, C0, C0, C1),
        arrayOf(C0, C0, C1, C0),
    )
)

data class QuantumBlockElem(val gate: QuantumGate, val indices: List<Int>)
data class QuantumBlock(val size: Int, val elems: List<QuantumBlockElem>) : QuantumGate

fun QuantumGate.blockElem(vararg indices: Int): QuantumBlockElem = QuantumBlockElem(this, indices.toList())