package quantum.math.array;

import org.junit.jupiter.api.Test;
import quantum.assertComplexVector
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.simple.complex.SimpleComplexCalculator

class ArrayComplexVectorTest {

    fun getCalc(): ComplexCalculator {
        return SimpleComplexCalculator()
    }

    @Test
    fun testSum() {
        val calc = getCalc()
        val v1 = arrayOf<ComplexExpression>(ComplexZero, ComplexZero, ComplexOne)
        val v2 = arrayOf<ComplexExpression>(ComplexZero, ComplexOne, ComplexZero)
        assertComplexVector(arrayOf(ComplexZero, ComplexOne, ComplexOne), v1.sum(v2, calc))
    }
}
