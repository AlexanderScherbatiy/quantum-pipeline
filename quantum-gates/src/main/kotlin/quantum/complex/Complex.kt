package quantum.complex

interface ComplexExpression

interface Complex : ComplexExpression

data class CartesianComplex(val real: Double, val image: Double = 0.0) : Complex

val ComplexZero = CartesianComplex(0.0, 0.0)
val ComplexOne = CartesianComplex(1.0, 0.0)
val ComplexImage = CartesianComplex(0.0, 1.0)

val C0 = ComplexZero
val C1 = ComplexOne
val CI = ComplexImage

data class ComplexUnaryMinus(val c: ComplexExpression) : ComplexExpression
data class ComplexSum(val c1: ComplexExpression, val c2: ComplexExpression) : ComplexExpression
data class ComplexMul(val c1: ComplexExpression, val c2: ComplexExpression) : ComplexExpression

fun Int.toComplex(): Complex = CartesianComplex(this.toDouble(), 0.0)

operator fun ComplexExpression.unaryMinus(): ComplexExpression = ComplexUnaryMinus(this)
operator fun ComplexExpression.plus(other: ComplexExpression): ComplexExpression = ComplexSum(this, other)
operator fun ComplexExpression.times(other: ComplexExpression): ComplexExpression = ComplexMul(this, other)
