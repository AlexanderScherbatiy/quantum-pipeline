package quantum.simple.pipeline

import org.junit.jupiter.api.Test
import quantum.ComplexResult
import quantum.assertQuantumState
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.pipeline.QuantumCalculator
import quantum.pipeline.QuantumGateControlledNot
import quantum.pipeline.QuantumGateX
import quantum.pipeline.QuantumPipeline
import quantum.pipeline.Qubit
import quantum.pipeline.QubitOne
import quantum.pipeline.QubitZero
import quantum.pipeline.tensor

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

        assertQuantumState(
            calc.calculate(
                QuantumPipeline(
                    Qubit(ComplexZero, ComplexOne),
                    listOf(QuantumGateX)
                )
            ),
            arrayOf(
                ComplexResult(1.0, 0.0),
                ComplexResult(0.0, 0.0)
            )
        )
    }

    @Test
    fun testControlledNot() {
        val calc = getCalculator()

        assertQuantumState(
            calc.calculate(
                QuantumPipeline(
                    QubitZero tensor QubitZero,
                    listOf(QuantumGateControlledNot)
                )
            ),
            arrayOf(
                ComplexResult(1.0, 0.0),
                ComplexResult(0.0, 0.0),
                ComplexResult(0.0, 0.0),
                ComplexResult(0.0, 0.0),
            )
        )
        assertQuantumState(
            calc.calculate(
                QuantumPipeline(
                    QubitZero tensor QubitOne,
                    listOf(QuantumGateControlledNot)
                )
            ),
            arrayOf(
                ComplexResult(0.0, 0.0),
                ComplexResult(1.0, 0.0),
                ComplexResult(0.0, 0.0),
                ComplexResult(0.0, 0.0),
            )
        )
    }
}