package quantum.simple.complex

import quantum.complex.CartesianComplex
import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.complex.ComplexMul
import quantum.complex.ComplexSum

class SimpleComplexCalculator : ComplexCalculator {

    override fun calculate(expression: ComplexExpression): Complex {
        val result = calc(expression)
        return CartesianComplex(result.real, result.image)
    }
}

private data class ComplexResult(val real: Double, val image: Double)

private fun calc(c: Complex): ComplexResult =
    when (c) {
        is CartesianComplex -> ComplexResult(c.real, c.image)
        else -> throw Error("Unknown complex: ${c}")
    }

private fun calc(expr: ComplexExpression): ComplexResult = when (expr) {
    is Complex -> calc(expr)
    is ComplexSum -> {
        val c1 = calc(expr.c1)
        val c2 = calc(expr.c2)
        ComplexResult(c1.real + c2.real, c1.image + c2.image)
    }
    is ComplexMul -> {
        val c1 = calc(expr.c1)
        val c2 = calc(expr.c2)
        ComplexResult(
            c1.real * c2.real - c1.image * c2.image,
            c1.real * c2.image + c1.image * c2.real
        )
    }
    else -> throw Error("Unknown complex: ${expr}")
}
