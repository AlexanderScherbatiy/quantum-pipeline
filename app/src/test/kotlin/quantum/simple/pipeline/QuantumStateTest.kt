package quantum.simple.pipeline

import org.junit.jupiter.api.Test
import quantum.assertQuantumState
import quantum.complex.C0
import quantum.complex.C1
import quantum.pipeline.QubitOne
import quantum.pipeline.QubitZero
import quantum.pipeline.tensor

class QuantumStateTest {

    @Test
    fun testTensor2() {
        assertQuantumState(QubitZero tensor QubitZero, arrayOf(C1, C0, C0, C0))
        assertQuantumState(QubitZero tensor QubitOne, arrayOf(C0, C1, C0, C0))
        assertQuantumState(QubitOne tensor QubitZero, arrayOf(C0, C0, C1, C0))
        assertQuantumState(QubitOne tensor QubitOne, arrayOf(C0, C0, C0, C1))
    }

    @Test
    fun testTensor3() {
        assertQuantumState(QubitZero tensor QubitZero tensor QubitZero, arrayOf(C1, C0, C0, C0, C0, C0, C0, C0 ))
        assertQuantumState(QubitZero tensor QubitOne tensor QubitZero, arrayOf(C0, C0, C1, C0, C0, C0, C0, C0 ))
        assertQuantumState(QubitOne tensor QubitOne tensor QubitOne, arrayOf(C0, C0, C0, C0, C0, C0, C0, C1 ))
    }
}