package quantum.simple.pipeline

import org.junit.jupiter.api.Test
import quantum.ComplexResult
import quantum.assertQuantumState
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.pipeline.QuantumCalculator
import quantum.pipeline.QuantumGateX
import quantum.pipeline.QuantumPipeline
import quantum.pipeline.Qubit

class QuantumPipelineTest {

    fun getCalculator(): QuantumCalculator {
        return SimpleQuantumCalculator()
    }

    @Test
    fun testPauliX() {
        val calc = getCalculator()

        assertQuantumState(
            calc.calculate(
                QuantumPipeline(
                    Qubit(ComplexOne, ComplexZero),
                    listOf(QuantumGateX)
                )
            ),
            arrayOf(
                ComplexResult(0.0, 0.0),
                ComplexResult(1.0, 0.0)
            )
        )
    }
}