import days.day13.Day13
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import java.io.File

class Day13Test {
    private val name = this.javaClass.simpleName.dropLast(4)
    private val input = File(this.javaClass.getResource("inputs/$name").file)
    private val sut = Day13(input)

    @Test
    fun `Part One`() {
        sut.partOne() `should be equal to` 295
    }

    @Test
    fun `Part Two`() {
        sut.partTwo() `should be equal to` 1068781L
    }
}