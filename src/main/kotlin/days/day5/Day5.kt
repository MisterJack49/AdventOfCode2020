package days.day5

import days.Day
import java.io.File
import java.util.*

class Day5(override val input: File) : Day {
    override fun partOne(): Long {
        val boardingPasses = input.readLines()

        return boardingPasses.map { findSeat(it) }
            .maxByOrNull { it.third }
            ?.third ?: 0L
    }

    fun findSeat(pass: String): Triple<Long, Long, Long> {
        val regex = Regex("^([F|B]{7})([R|L]{3})$")
        val (rowLoc, columnLoc) = regex.matchEntire(pass)!!.destructured

        val row = rowLoc.locationToLong()
        val col = columnLoc.locationToLong()

        return Triple(row, col, (row * 8) + col)
    }

    private fun String.locationToLong() =
        this.reversed().let { reversed ->
            val bitSet = BitSet(reversed.length)
            reversed.indices.forEach {
                bitSet.set(it, reversed[it].toBoolean())
            }
            bitSet.toLong()
        }

    private fun Char.toBoolean(): Boolean = when (this) {
        'R', 'B' -> true
        else -> false
    }

    private fun BitSet.toLong(): Long {
        var value = 0L
        for (i in 0 until this.length()) {
            value += if (this.get(i)) (1L shl i) else 0L
        }
        return value
    }

    override fun partTwo(): List<Long> {
        val boardingPasses = input.readLines()

        val seats = boardingPasses
            .map { findSeat(it).third }
            .sorted()

        val refSeats = (seats[0]..partOne()).toList()

        return refSeats.minus(seats)
    }
}