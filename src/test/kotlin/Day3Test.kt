import days.Day3
import org.amshove.kluent.`should be equal to`
import java.io.File
import kotlin.test.Test

class Day3Test {
    private val name = this.javaClass.simpleName.dropLast(4)
    private val input = File(this.javaClass.getResource("inputs/$name").file)
    private val sut = Day3(input)

    @Test
    fun `Part One`(){
        sut.partOne() `should be equal to` 7
    }

    @Test
    fun `Part Two`(){
        sut.partTwo() `should be equal to` 336
    }
}