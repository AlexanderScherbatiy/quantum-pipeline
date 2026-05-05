package quantum.math.array

import quantum.complex.C0
import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.complex.plus
import quantum.complex.times

typealias ComplexExpressionVector = Array<ComplexExpression>
typealias ComplexVector = Array<Complex>

fun ComplexExpressionVector.sum(other: ComplexExpressionVector, calc: ComplexCalculator): ComplexVector {
    return Array(this.size) { i -> calc.calculate(this[i].plus(other[i])) }
}
fun ComplexExpressionVector.tensor(other:ComplexExpressionVector, calc: ComplexCalculator): ComplexVector {
    val v1 = this
    val v2 = other
    val result = Array<Complex>(v1.size * v2.size) { C0 }
    var index = 0
    for (i in 0 until v1.size) {
        for (j in 0 until v2.size) {
            result[index++] = calc.calculate(v1[i] * v2[j])
        }
    }
    return result
}
