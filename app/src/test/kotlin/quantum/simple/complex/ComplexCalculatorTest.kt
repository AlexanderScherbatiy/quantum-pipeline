package quantum.simple.complex

import org.junit.jupiter.api.Test
import quantum.complex.CartesianComplex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexImage
import quantum.complex.ComplexOne
import quantum.complex.ComplexZero
import quantum.complex.sum
import quantum.assertComplex
import quantum.complex.mul

class ComplexCalculatorTest {

    fun getCalculator(): ComplexCalculator {
        return SimpleComplexCalculator()
    }

    @Test
    fun testConstants() {
        val calc = getCalculator()
        assertComplex(calc.calculate(ComplexZero), 0.0, 0.0)
        assertComplex(calc.calculate(ComplexOne), 1.0, 0.0)
        assertComplex(calc.calculate(ComplexImage), 0.0, 1.0)
    }

    @Test
    fun testSum() {
        val calc = getCalculator()
        assertComplex(calc.calculate(ComplexOne.sum(ComplexImage)), 1.0, 1.0)
        val c1 = CartesianComplex(1.0, 2.0)
        val c2 = CartesianComplex(3.0, 4.0)
        assertComplex(calc.calculate(c1.sum(c2)), 4.0, 6.0)
    }

    @Test
    fun testMul() {
        val calc = getCalculator()
        assertComplex(calc.calculate(ComplexOne.mul(ComplexImage)), 0.0, 1.0)
        assertComplex(calc.calculate(ComplexOne.mul(ComplexOne)), 1.0, 0.0)
        assertComplex(calc.calculate(ComplexImage.mul(ComplexImage)), -1.0, 0.0)
        val c1 = CartesianComplex(1.0, 2.0)
        val c2 = CartesianComplex(3.0, 4.0)
        assertComplex(calc.calculate(c1.mul(c2)), -5.0, 10.0)
    }
}

