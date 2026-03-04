package quantum.simple.math

import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.complex.ComplexZero
import quantum.complex.times
import quantum.complex.plus

typealias ComplexExpressionVector = Array<ComplexExpression>
typealias ComplexVector = Array<Complex>
typealias ComplexMatrix = Array<Array<Complex>>
typealias ComplexExpressionMatrix = Array<Array<ComplexExpression>>

class SimpleMath {

    private val complexCalc: ComplexCalculator

    constructor(complexCalc: ComplexCalculator) {
        this.complexCalc = complexCalc
    }

    fun calc(complex: ComplexExpression): Complex = complexCalc.calculate(complex)

    fun mul(vector: ComplexExpressionVector, matrix: ComplexExpressionMatrix): Array<Complex> {
        return matrix.map({ row ->
            var c: Complex = ComplexZero
            for (i in 0 until row.size) {
                val s = vector[i]
                val r = row[i]
                val expr = c + s * r
                c = this.complexCalc.calculate(expr)
            }
            c
        }).toTypedArray()
    }

    fun tensor(values: Array<ComplexExpressionVector>): ComplexVector {
        if (values.size != 2) throw Error("Not supported number of values: ${values.size}")
        val v1 = values[0]
        val v2 = values[1]
        return arrayOf(v1[0] * v2[0], v1[0] * v2[1], v1[1] * v2[0], v1[1] * v2[1])
            .map { complexCalc.calculate(it) }.toTypedArray()
    }
}
