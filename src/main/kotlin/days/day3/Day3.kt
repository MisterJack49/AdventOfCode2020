package days.day3

import days.Day
import java.io.File

class Day3(override val input: File) : Day {
    override fun partOne(): Int {
        return countTreeOnSlope(3, 1)
    }

    override fun partTwo(): Long {
        return listOf(
            Pair(1, 1),
            Pair(3, 1),
            Pair(5, 1),
            Pair(7, 1),
            Pair(1, 2)
        )
            .map { countTreeOnSlope(it.first, it.second) }
            .fold(1L, { acc, value -> acc * value })
    }

    private fun Char.isTree() = this == '#'

    private fun countTreeOnSlope(dX: Int, dY: Int): Int {
        val parsed = input.readLines()
            .map { it.toCharArray() }

        val maxX = parsed.first().count()
        val maxY = parsed.count()

        var x = 0
        var y = 0
        var count = 0

        while (y < maxY) {
            if (parsed[y][x].isTree()) count++
            x = (x + dX) % maxX
            y += dY
        }

        return count
    }
}