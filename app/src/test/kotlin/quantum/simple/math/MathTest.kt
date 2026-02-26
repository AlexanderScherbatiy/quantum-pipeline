package quantum.simple.math;

import org.junit.jupiter.api.Test;
import quantum.complex.ComplexExpression
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.simple.complex.SimpleComplexCalculator

class MathTest {

    fun getMath(): SimpleMath {
        return SimpleMath(SimpleComplexCalculator())
    }

    @Test
    fun testVectorMatrixMul() {
        val math = getMath()
        val vector = arrayOf<ComplexExpression>(ComplexZero, ComplexOne)
        val matrix = arrayOf(
            arrayOf<ComplexExpression>(ComplexOne, ComplexZero),
            arrayOf<ComplexExpression>(ComplexZero, ComplexOne)
        )
        val result = math.mul(vector, matrix)
    }
}
