package days.day15

import days.Day
import java.io.File

class Day15(override val input: File) : Day {
    override fun partOne(): Any {
        val turns = input.readLines().first().split(",").map { it.toInt() }

        return getValueAtTurn(turns, 2020)
    }

    override fun partTwo(): Any {
        val turns = input.readLines().first().split(",").map { it.toInt() }

        return getValueAtTurn(turns, 30000000)
    }

    companion object {
        fun getValueAtTurn(initialTurns: List<Int>, target: Int): Int {
            val spoken = mutableMapOf<Int, Pair<Int, Int>>()

            initialTurns.withIndex()
                .forEach { spoken[it.value] = Pair(it.index + 1, it.index + 1) }

            var turn = initialTurns.size + 1
            var last = initialTurns.last()
            while (turn <= target) {
                val lastSpoken = spoken[last]!!

                last = if (lastSpoken.first != lastSpoken.second) {
                    val updateKey = lastSpoken.first - lastSpoken.second
                    if (spoken.containsKey(updateKey)) {
                        val previous = spoken[updateKey]!!
                        spoken[updateKey] = Pair(turn, previous.first)
                    } else {
                        spoken[updateKey] = Pair(turn, turn)
                    }
                    updateKey
                } else {
                    if (spoken.containsKey(0)) {
                        val previous = spoken[0]!!
                        spoken[0] = Pair(turn, previous.first)
                    } else {
                        spoken[0] = Pair(turn, turn)
                    }
                    0
                }
                turn++
            }

            return last
        }
    }
}