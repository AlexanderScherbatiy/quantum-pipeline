package quantum.simple.pipeline

import org.junit.jupiter.api.Test
import quantum.pipeline.QuantumGateX
import quantum.assertQuantumGate
import quantum.complex.C0
import quantum.complex.C1
import quantum.pipeline.QuantumGateControlledNot
import quantum.toResult

class QuantumGateTest {

    @Test
    fun testPauliGates() {
        assertQuantumGate(
            QuantumGateX,
            arrayOf(
                arrayOf(C0.toResult(), C1.toResult()),
                arrayOf(C1.toResult(), C0.toResult()),
            )
        )
    }

    @Test
    fun testControlledNot() {
        assertQuantumGate(
            QuantumGateControlledNot,
            arrayOf(
                arrayOf(C1.toResult(), C0.toResult(), C0.toResult(), C0.toResult()),
                arrayOf(C0.toResult(), C1.toResult(), C0.toResult(), C0.toResult()),
                arrayOf(C0.toResult(), C0.toResult(), C0.toResult(), C1.toResult()),
                arrayOf(C0.toResult(), C0.toResult(), C1.toResult(), C0.toResult()),
            )
        )
    }
}