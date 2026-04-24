package quantum.simple.math

import quantum.complex.*
import quantum.math.array.ComplexExpressionVector
import quantum.math.array.ComplexVector

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

    fun mul(matrix1: ComplexExpressionMatrix, matrix2: ComplexExpressionMatrix): ComplexExpressionMatrix {
        return Array(matrix1.size) {
            n -> Array(matrix2[0].size) {
                // TBD:
                j -> ComplexOne
            } }
    }

    fun tensor(vectors: Array<ComplexExpressionVector>): ComplexVector {
        if (vectors.size != 2) throw Error("Not supported number of values: ${vectors.size}")
        val v1 = vectors[0]
        val v2 = vectors[1]
        return arrayOf(v1[0] * v2[0], v1[0] * v2[1], v1[1] * v2[0], v1[1] * v2[1])
            .map { complexCalc.calculate(it) }
            .toTypedArray()
    }

    fun tensor(matrices: Array<ComplexExpressionMatrix>): ComplexMatrix {
        if (matrices.size != 2) throw Error("Not supported number of values: ${matrices.size}")

        val m1 = matrices[0]
        val m2 = matrices[1]

        val rowSize1 = m1.size
        val colSize1 = m1[0].size
        val rowSize2 = m2.size
        val colSize2 = m2[0].size

        val m = Array(rowSize1 * rowSize2) { Array<Complex>(colSize1 * colSize2) { C0 } }

        var row = 0
        for (i1 in 0 until rowSize1) {
            for (i2 in 0 until rowSize2) {
                var col = 0
                for (j1 in 0 until colSize1) {
                    for (j2 in 0 until colSize2) {
                        m[row + i2][col + j2] = this.calc(m1[i1][j1] * m2[i2][j2])
                    }
                    col += colSize2
                }
            }
            row += rowSize2
        }
        return m
    }
}
