package quantum

import quantum.complex.ComplexExpression
import quantum.complex.toComplex

interface QuntumGate

data class ArrayQuantumGate(val data: Array<Array<ComplexExpression>>) : QuntumGate

val QuantumGateX = ArrayQuantumGate(
    arrayOf(
        arrayOf(0.toComplex(), 1.toComplex()),
        arrayOf(1.toComplex(), 0.toComplex())
    )
)
