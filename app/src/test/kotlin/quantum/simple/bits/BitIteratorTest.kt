package quantum.simple.bits

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import quantum.bits.BitIterator

class BitIteratorTest {

    @Test
    fun testBitIterator() {

        val bitsList0 = getBitsList(0)
        Assertions.assertEquals(0, bitsList0.size)
        Assertions.assertEquals(listOf<List<Boolean>>(), bitsList0)

        val bitsList1 = getBitsList(1)
        Assertions.assertEquals(2, bitsList1.size)
        Assertions.assertEquals(listOf(listOf(false), listOf(true)), bitsList1)

        val bitsList2 = getBitsList(2)
        Assertions.assertEquals(4, bitsList2.size)
        Assertions.assertEquals(
            listOf(
                listOf(false, false),
                listOf(true, false),
                listOf(false, true),
                listOf(true, true),
            ),
            bitsList2
        )

        val bitsList3 = getBitsList(3)
        Assertions.assertEquals(8, bitsList3.size)
        Assertions.assertEquals(
            listOf(
                listOf(false, false, false),
                listOf(true, false, false),
                listOf(false, true, false),
                listOf(true, true, false),
                listOf(false, false, true),
                listOf(true, false, true),
                listOf(false, true, true),
                listOf(true, true, true),
            ),
            bitsList3
        )
    }

    fun getBitsList(size: Int): List<List<Boolean>> {

        val bitsList = mutableListOf<List<Boolean>>()
        val iter = BitIterator(size)
        for (bits in iter) {
            bitsList.add(bits.toList())
        }
        return bitsList
    }
}