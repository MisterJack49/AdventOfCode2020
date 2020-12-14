import days.Day7
import days.day14.Day14
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import java.io.File

class Day14Test {
    private val name = this.javaClass.simpleName.dropLast(4)

    @Test
    fun `Part One`() {
        val sut = Day14(File(this.javaClass.getResource("inputs/$name-Part1").file))
        sut.partOne() `should be equal to` 165L
    }

    @Test
    fun `Part Two`() {
        val sut = Day14(File(this.javaClass.getResource("inputs/$name-Part2").file))
        sut.partTwo() `should be equal to` 208L
    }
}