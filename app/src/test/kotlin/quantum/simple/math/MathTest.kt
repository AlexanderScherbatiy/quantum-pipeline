package quantum.simple.math;

import org.junit.jupiter.api.Test;
import quantum.assertMatrix
import quantum.complex.Complex
import quantum.complex.ComplexExpression
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.complex.toComplex
import quantum.simple.complex.SimpleComplexCalculator

private fun Int.c(): Complex = this.toComplex()
private fun Int.e(): ComplexExpression = this.toComplex()

class MathTest {

    fun getMath(): SimpleMath {
        return SimpleMath(SimpleComplexCalculator())
    }

    @Test
    fun testVectorMatrixMul() {
        val math = getMath()
        val vector = arrayOf<ComplexExpression>(ComplexZero, ComplexOne)
        val matrix = arrayOf(
            arrayOf<ComplexExpression>(ComplexOne, ComplexZero), arrayOf<ComplexExpression>(ComplexZero, ComplexOne)
        )
        val result = math.mul(vector, matrix)
    }

    @Test
    fun testTensorMatrix() {
        val math = getMath()
        val m1 = arrayOf(arrayOf(1.e(), 2.e()))
        val m2 = arrayOf(
            arrayOf(3.e(), 4.e(), 5.e()),
            arrayOf(6.e(), 7.e(), 8.e()),
        )

        assertMatrix(
            math.tensor(arrayOf(m1, m2)),
            arrayOf(
                arrayOf(3.c(), 4.c(), 5.c(), 6.c(), 8.c(), 10.c()),
                arrayOf(6.c(), 7.c(), 8.c(), 12.c(), 14.c(), 16.c())
            )
        )

        assertMatrix(
            math.tensor(arrayOf(m2, m1)),
            arrayOf(
                arrayOf(3.c(), 6.c(), 4.c(), 8.c(), 5.c(), 10.c()),
                arrayOf(6.c(), 12.c(), 7.c(), 14.c(), 8.c(), 16.c())
            )
        )
    }
}
