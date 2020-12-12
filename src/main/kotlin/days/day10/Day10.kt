package days.day10

import days.Day
import java.io.File

class Day10(override val input: File) : Day {
    override fun partOne(): Any {
        val adapters = listOf(0)
            .plus(input.readLines().map { it.toInt() }.sorted())
            .let { it.plus(it.last() + 3) }

        var ones = 0
        var threes = 0
        for (i in 0 until adapters.count() - 1) {
            when (adapters[i + 1] - adapters[i]) {
                1 -> ones++
                3 -> threes++
            }
        }

        return ones * threes
    }

    override fun partTwo(): Long {
        val adapters = listOf(0)
            .plus(input.readLines().map { it.toInt() }.sorted())
            .let { it.plus(it.last() + 3) }

        val groups = mutableListOf<List<Int>>()
        val group = mutableListOf<Int>()
        for (i in 0 until adapters.count() - 1) {
            when (adapters[i + 1] - adapters[i]) {
                1 -> group.add(adapters[i])
                3 -> {
                    group.add(adapters[i])
                    groups.add(group.toList())
                    group.clear()
                }
            }
        }
        group.add(adapters.last())
        groups.add(group)

        return groups
            .map {
                when (it.size) {
                    1 -> 1
                    2 -> 1
                    3 -> 2 // 2^1 possibilities
                    4 -> 4 // 2^2 possibilities
                    5 -> 7 // 2^3 -1 possibilities
                    else -> 0
                }
            }
            .fold(1L) { acc, i -> acc * i }
    }
}