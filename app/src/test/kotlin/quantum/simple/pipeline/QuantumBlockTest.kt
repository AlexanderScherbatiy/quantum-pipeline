package quantum.simple.pipeline

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import quantum.assertQuantumState
import quantum.complex.C0
import quantum.complex.CI
import quantum.complex.unaryMinus
import quantum.pipeline.QuantumBlock
import quantum.pipeline.QuantumCalculator
import quantum.pipeline.QuantumGateControlledNot
import quantum.pipeline.QuantumGateX
import quantum.pipeline.QuantumGateY
import quantum.pipeline.QuantumPipeline
import quantum.pipeline.QubitOne
import quantum.pipeline.QubitZero
import quantum.pipeline.tensor
import quantum.pipeline.blockElem

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
                QuantumGateX.blockElem(0),
                QuantumGateY.blockElem(1),
            )
        )
        assertQuantumState(
            calc.calculate(
                QuantumPipeline(QubitZero tensor QubitOne, listOf(xy))
            ), arrayOf(C0, C0, -CI, C0)
        )
    }

    @Test
    @Disabled
    fun testMAJ() {
        val calc = getCalculator()
        val majBlock = QuantumBlock(
            3, listOf(
                QuantumGateControlledNot.blockElem(2, 1)
            )
        )
        val state = QubitOne tensor QubitOne tensor QubitOne
        assertQuantumState(
            calc.calculate(
                QuantumPipeline(state, listOf(majBlock))
            ), arrayOf(C0, C0, -CI, C0)
        )
    }
}