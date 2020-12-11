import days.*
import java.io.File

fun main() {
    (1..11)
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
        else -> throw NotImplementedError()
    }
}