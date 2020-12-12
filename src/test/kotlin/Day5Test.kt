import days.day5.Day5
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import java.io.File

class Day5Test {
    private val name = this.javaClass.simpleName.dropLast(4)
    private val input = File(this.javaClass.getResource("inputs/$name").file)
    private val sut = Day5(input)

    @Test
    fun `Find seat BFFFBBFRRR`(){
        val test = sut.findSeat("BFFFBBFRRR")
        test.first `should be equal to` 70L
        test.second `should be equal to` 7L
        test.third `should be equal to` 567L
    }

    @Test
    fun `Find seat FFFBBBFRRR`(){
        val test = sut.findSeat("FFFBBBFRRR")
        test.first `should be equal to` 14L
        test.second `should be equal to` 7L
        test.third `should be equal to` 119L
    }

    @Test
    fun `Find seat BBFFBBFRLL`(){
        val test = sut.findSeat("BBFFBBFRLL")
        test.first `should be equal to` 102L
        test.second `should be equal to` 4L
        test.third `should be equal to` 820L
    }

    @Test
    fun `Part One`(){
        sut.partOne() `should be equal to` 820L
    }
}