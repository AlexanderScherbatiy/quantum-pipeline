package quantum.bits

interface BitExpression

interface Bit : BitExpression

object BitZero : BitExpression
object BitOne : BitExpression

fun Boolean.toBit() = if (this) BitOne else BitZero

fun Array<Boolean>.toIndex(): Int {
    var index = 0
    for (i in this.size - 1 downTo 0) {
        val bit = this[i]
        index = index shl 1
        if (bit) index++
    }
    return index
}

class BitIterator(private val size: Int) : Iterator<Array<Boolean>> {

    var finished = size == 0
    val bits = Array<Boolean>(size) { false }

    override fun hasNext(): Boolean = !finished

    override fun next(): Array<Boolean> {
        val res = bits.copyOf()
        for (i in 0 until size) {
            if (!bits[i]) {
                bits[i] = true
                break
            }
            bits[i] = false
            if (i == size - 1) {
                finished = true
            }
        }
        return res
    }
}

