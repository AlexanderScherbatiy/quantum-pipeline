package quantum.math.array

import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.complex.plus

typealias ComplexExpressionVector = Array<ComplexExpression>
typealias ComplexVector = Array<Complex>

fun ComplexExpressionVector.sum(other: ComplexExpressionVector, calc: ComplexCalculator): ComplexVector {
    return Array(this.size) { i -> calc.calculate(this[i].plus(other[i])) }
}
