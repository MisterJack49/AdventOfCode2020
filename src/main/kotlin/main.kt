import days.*
import java.io.File

fun main() {
    listOf(1, 2, 3, 4, 5, 6, 7, 8)
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
        2 -> Day2(input)
        3 -> Day3(input)
        4 -> Day4(input)
        5 -> Day5(input)
        6 -> Day6(input)
        7 -> Day7(input)
        8 -> Day8(input)
        else -> throw NotImplementedError()
    }
}