package quantum.math.array;

import org.junit.jupiter.api.Test;
import quantum.assertComplexVector
import quantum.complex.C0
import quantum.complex.C1
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.simple.complex.SimpleComplexCalculator

class ArrayComplexVectorTest {

    fun getCalc(): ComplexCalculator {
        return SimpleComplexCalculator()
    }

    @Test
    fun testSum() {
        val calc = getCalc()
        val v1 = arrayOf<ComplexExpression>(C0, C0, C1)
        val v2 = arrayOf<ComplexExpression>(C0, C1, C0)
        assertComplexVector(arrayOf(C0, C1, C1), v1.sum(v2, calc))
    }
}
