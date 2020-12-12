package days.day1

import days.Day
import java.io.File

class Day1(override val input: File) : Day {

    override fun partOne(): Int {
        val parsed = input.readLines()
            .map { it.toInt() }

        val seen = mutableListOf<Int>()
        parsed.forEach {
            val target = 2020 - it
            if (seen.contains(target)) {
                return (target * it)
            }
            seen.add(it)
        }
        return 0
    }

    override fun partTwo(): Int {
        val parsed = input.readLines()
            .map { it.toInt() }

        parsed.forEach { first ->
            val reduced = parsed.minus(first)
            val target = 2020 - first

            val seen = mutableListOf<Int>()
            reduced.forEach { second ->
                val subTarget = target - second
                if (seen.contains(subTarget)) {
                    return (subTarget * second * first)
                }
                seen.add(second)
            }
        }
        return 0
    }

}