import days.Day7
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import java.io.File

class Day7Test {
    private val name = this.javaClass.simpleName.dropLast(4)

    @Test
    fun `Part One`(){
        val sut = Day7(File(this.javaClass.getResource("inputs/$name-Part1").file))
        sut.partOne() `should be equal to` 4
    }

    @Test
    fun `Part Two`(){
        val sut = Day7(File(this.javaClass.getResource("inputs/$name-Part2").file))
        sut.partTwo() `should be equal to` 126
    }
}