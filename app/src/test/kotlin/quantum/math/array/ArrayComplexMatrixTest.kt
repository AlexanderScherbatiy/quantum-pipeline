package quantum.math.array;

import org.junit.jupiter.api.Test;
import quantum.assertComplexVector
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.simple.complex.SimpleComplexCalculator

class ArrayComplexMatrixTest {

    fun getCalc(): ComplexCalculator {
        return SimpleComplexCalculator()
    }

    @Test
    fun testMatrixVectorMul() {
        val calc = getCalc()
        val v = arrayOf<ComplexExpression>(ComplexZero, ComplexZero, ComplexOne)
        val m = arrayOf<ComplexExpression>(ComplexZero, ComplexOne, ComplexZero)
        assertComplexVector(arrayOf(ComplexZero, ComplexOne, ComplexOne), v1.sum(v2, calc))
    }
}
