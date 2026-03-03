package quantum.simple.pipeline

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import quantum.assertQuantumState
import quantum.complex.C0
import quantum.complex.CI
import quantum.complex.unaryMinus
import quantum.pipeline.QuantumBlock
import quantum.pipeline.QuantumCalculator
import quantum.pipeline.QuantumGateX
import quantum.pipeline.QuantumGateY
import quantum.pipeline.QuantumPipeline
import quantum.pipeline.QubitOne
import quantum.pipeline.QubitZero
import quantum.pipeline.tensor
import quantum.pipeline.toElem


class QuantumBlockTest {

    fun getCalculator(): QuantumCalculator {
        return SimpleQuantumCalculator()
    }

    @Test
    @Disabled
    fun testXY() {
        val calc = getCalculator()
        val xy = QuantumBlock(
            2, listOf(
                QuantumGateX.toElem(0),
                QuantumGateY.toElem(1),
            )
        )
        assertQuantumState(
            calc.calculate(
                QuantumPipeline(QubitZero tensor QubitOne, listOf(xy))
            ), arrayOf(C0, C0, -CI, C0)
        )
    }
}