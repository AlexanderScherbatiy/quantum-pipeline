package quantum.math.array;

import org.junit.jupiter.api.Test;
import quantum.assertComplexVector
import quantum.complex.C0
import quantum.complex.C1
import quantum.complex.CartesianComplex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.simple.complex.SimpleComplexCalculator

class ArrayComplexMatrixTest {

    fun getCalc(): ComplexCalculator {
        return SimpleComplexCalculator()
    }

    @Test
    fun testMatrixVectorMul() {
        val calc = getCalc()
        val v = arrayOf<ComplexExpression>(C1, C0, C1)
        val m = arrayOf<Array<ComplexExpression>>(
            arrayOf(C1, C0, C1),
            arrayOf(C0, C1, C1),
            arrayOf(C1, C1, C0),
        )
        assertComplexVector(arrayOf(CartesianComplex(2.0, 0.0), C1, C1), m.mul(v, calc))
    }
}
