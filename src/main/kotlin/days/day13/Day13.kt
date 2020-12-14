package days.day13

import days.Day
import java.io.File

class Day13(override val input: File) : Day {
    override fun partOne(): Any {
        val (target, buses) = input.readLines().let { list ->
            list.first().toInt() to list.last()
                .split(',')
                .filter { it != "x" }
                .map { it.toInt() }
                .toList()
        }

        val interval = buses.toList()
            .map {
                var inc = it
                while (inc < target)
                    inc += it
                inc
            }

        val closest = interval.filter { it >= target }.minOrNull()!!
        val bus = buses[interval.indexOf(closest)]
        return (closest - target) * bus
    }

    override fun partTwo(): Any {
        val buses = input.readLines().last()
            .split(',')
            .map { if (it == "x") 0 else it.toLong() }
            .withIndex()
            .filter { it.value > 0 }

        var step = buses.first().value
        var time = 0L
        buses.drop(1).forEach { (offset, bus) ->
            while ((time + offset) % bus != 0L) {
                time += step
            }
            step *= bus
        }
        return time
    }

}