package quantum.pipeline

import quantum.complex.ComplexExpression

interface QuantumState

data class Qubit(val c1: ComplexExpression, val c2: ComplexExpression) : QuantumState


