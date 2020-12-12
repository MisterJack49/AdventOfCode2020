import days.*
import days.day1.Day1
import days.day10.Day10
import days.day2.Day2
import days.day3.Day3
import days.day4.Day4
import days.day5.Day5
import days.day6.Day6
import days.day9.Day9
import java.io.File

fun main() {
    (1..12)
        .map { getDay(it) }
        .map { println(it.prettyPrint()) }
}

fun Day.prettyPrint() =
    """
       --- ${this::class.simpleName} ---
        * Part One: ${partOne()}
        * Part Two: ${partTwo()}       
        
    """.trimMargin()

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
        9 -> Day9(input)
        10 -> Day10(input)
        11 -> Day11(input)
        12 -> Day12(input)
        else -> throw NotImplementedError()
    }
}