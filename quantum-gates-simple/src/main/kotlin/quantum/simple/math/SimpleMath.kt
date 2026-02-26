package quantum.simple.math

import quantum.complex.Complex
import quantum.complex.ComplexCalculator
import quantum.complex.ComplexExpression
import quantum.complex.ComplexZero
import quantum.complex.times
import quantum.complex.plus

class SimpleMath {

    private val complexCalc: ComplexCalculator

    constructor(complexCalc: ComplexCalculator) {
        this.complexCalc = complexCalc
    }

    fun mul(vector: Array<ComplexExpression>, matrix: Array<Array<ComplexExpression>>): Array<Complex> {
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
}
