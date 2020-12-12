import days.day2.Day2
import org.amshove.kluent.`should be equal to`
import java.io.File
import kotlin.test.Test

class Day2Test {
    private val name = this.javaClass.simpleName.dropLast(4)
    private val input = File(this.javaClass.getResource("inputs/$name").file)
    private val sut = Day2(input)

    @Test
    fun `Part One`(){
        sut.partOne() `should be equal to` 2
    }

    @Test
    fun `Part Two`(){
        sut.partTwo() `should be equal to` 1
    }
}