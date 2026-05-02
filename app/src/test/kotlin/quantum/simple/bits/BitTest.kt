package quantum.simple.bits

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import quantum.bits.toIndex

class BitTest {

    @Test
    fun testToIndex() {

        Assertions.assertEquals(0, arrayOf<Boolean>().toIndex())

        Assertions.assertEquals(0, arrayOf(false).toIndex())
        Assertions.assertEquals(1, arrayOf(true).toIndex())

        Assertions.assertEquals(2, arrayOf(false, true).toIndex())
        Assertions.assertEquals(3, arrayOf(true, true).toIndex())

        Assertions.assertEquals(4, arrayOf(false, false, true).toIndex())
        Assertions.assertEquals(5, arrayOf(true, false, true).toIndex())
        Assertions.assertEquals(6, arrayOf(false, true, true).toIndex())
        Assertions.assertEquals(7, arrayOf(true, true, true).toIndex())
    }
}