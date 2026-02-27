package quantum.pipeline

import quantum.complex.C0
import quantum.complex.C1
import quantum.complex.ComplexExpression

interface QuantumStateExpression

interface QuantumState : QuantumStateExpression

data class Qubit(val c1: ComplexExpression, val c2: ComplexExpression) : QuantumState

val QubitZero = Qubit(C1, C0)
val QubitOne = Qubit(C0, C1)

data class QuantumStateArray(val values: Array<ComplexExpression>) : QuantumState

data class QuantumStateTensor(val states: Array<QuantumStateExpression>) : QuantumStateExpression

infix fun QuantumStateExpression.tensor(other: QuantumStateExpression): QuantumStateExpression =
    QuantumStateTensor(arrayOf(this, other))