import days.day4.Day4
import org.amshove.kluent.`should be equal to`
import java.io.File
import kotlin.test.Test

class Day4Test {
    private val name = this.javaClass.simpleName.dropLast(4)

    @Test
    fun `Part One`(){
        val sut = Day4(File(this.javaClass.getResource("inputs/$name-Part1").file))
        sut.partOne() `should be equal to` 2
    }

    @Test
    fun `Part Two Valid Passport`(){
        val sut = Day4(File(this.javaClass.getResource("inputs/$name-Part2").file))
        sut.partTwo() `should be equal to` 4
    }
}