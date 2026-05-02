package quantum.math.array

import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.complex.ComplexZero
import quantum.complex.plus
import quantum.complex.times

typealias ComplexMatrix = Array<Array<Complex>>
typealias ComplexExpressionMatrix = Array<Array<ComplexExpression>>

fun ComplexExpressionMatrix.mul(vector: ComplexExpressionVector, calc: ComplexCalculator): ComplexVector {
    return this.map({ row ->
        var c: Complex = ComplexZero
        for (i in 0 until row.size) {
            val s = vector[i]
            val r = row[i]
            val expr = c + s * r
            c = calc.calculate(expr)
        }
        c
    }).toTypedArray()
}
