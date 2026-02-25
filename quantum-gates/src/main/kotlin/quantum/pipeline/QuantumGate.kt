package quantum.pipeline

import quantum.complex.ComplexExpression
import quantum.complex.toComplex

interface QuantumGate

data class ArrayQuantumGate(val data: Array<Array<ComplexExpression>>) : QuantumGate

val QuantumGateX = ArrayQuantumGate(
    arrayOf(
        arrayOf(0.toComplex(), 1.toComplex()),
        arrayOf(1.toComplex(), 0.toComplex())
    )
)
