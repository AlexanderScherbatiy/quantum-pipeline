package quantum.simple.pipeline

import org.junit.jupiter.api.Test
import quantum.pipeline.QuantumGateX
import quantum.assertQuantumGate
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.toResult

class QuantumGateTest {

    @Test
    fun testPauliGates() {
        assertQuantumGate(
            QuantumGateX, arrayOf(
                arrayOf(ComplexZero.toResult(), ComplexOne.toResult()),
                arrayOf(ComplexOne.toResult(), ComplexZero.toResult()),
            )
        )
    }
}