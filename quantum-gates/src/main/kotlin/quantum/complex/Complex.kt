package quantum.complex

interface ComplexExpression

interface Complex : ComplexExpression

data class CartesianComplex(val real: Double, val image: Double) : Complex

val ComplexZero = CartesianComplex(0.0, 0.0)
val ComplexOne = CartesianComplex(1.0, 0.0)
val ComplexImage = CartesianComplex(0.0, 1.0)

data class ComplexSum(val c1: ComplexExpression, val c2: ComplexExpression) : ComplexExpression

fun Int.toComplex(): Complex = CartesianComplex(this.toDouble(), 0.0)

fun ComplexExpression.sum(other: ComplexExpression): ComplexExpression = ComplexSum(this, other)