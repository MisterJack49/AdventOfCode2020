
import days.Day10
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import java.io.File

class Day10Test {

    private val name = this.javaClass.simpleName.dropLast(4)


    @Test
    fun `Part One Set One`() {
        val input = File(this.javaClass.getResource("inputs/${name}-Set1").file)
        val sut = Day10(input)
        sut.partOne() `should be equal to` 35
    }

    @Test
    fun `Part Two Set One`() {
        val input = File(this.javaClass.getResource("inputs/${name}-Set1").file)
        val sut = Day10(input)
        sut.partTwo() `should be equal to` 8
    }

    @Test
    fun `Part One Set Two`() {
        val input = File(this.javaClass.getResource("inputs/${name}-Set2").file)
        val sut = Day10(input)
        sut.partOne() `should be equal to` 220
    }

    @Test
    fun `Part Two Set Two`() {
        val input = File(this.javaClass.getResource("inputs/${name}-Set2").file)
        val sut = Day10(input)
        sut.partTwo() `should be equal to` 19208
    }
}