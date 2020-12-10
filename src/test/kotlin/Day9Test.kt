import days.Day9
import org.amshove.kluent.`should be equal to`
import org.junit.Test
import java.io.File

class Day9Test {
    private val name = this.javaClass.simpleName.dropLast(4)
    private val input = File(this.javaClass.getResource("inputs/$name").file)
    private val sut = Day9(input)

    @Test
    fun `Find weakness`() {
        sut.findWeakness(input.readLines().map { it.toLong() }, 5) `should be equal to` 127
    }

    @Test
    fun `Find weakness sum`() {
        sut.findWeaknessSum(input.readLines().map { it.toLong() }, 127) `should be equal to` listOf(15L, 25L, 47L, 40L)
    }

    @Test
    fun `Find min max sum result`() {
        sut.getMinMaxSum(listOf(15L, 25L, 47L, 40L)) `should be equal to` 62
    }
}