import days.*
import java.io.File

fun main() {
    listOf(1)
        .map { getResult(it) }
}

fun getResult(date: Int) {
    val day = getDay(date)
    println("--- ${day::class.simpleName} ---")
    println("Part One: ${day.partOne()}")
    println("Part Two: ${day.partTwo()}")
    println()
}
fun getDay(date: Int): Day {
    val input = File({}::class.java.getResource("inputs/Day$date").file)

    return when (date) {
        1 -> Day1(input)
        else -> throw NotImplementedError()
    }
}