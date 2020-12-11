package days

import java.io.File

class Day11(override val input: File) : Day {
    override fun partOne(): Any {
        val area = input.readLines().map { it.toCharArray() }

        var previousArea = area.copy()
        var newArea = previousArea.mutateAreaPart1()

        while (!(previousArea isSameAs newArea)) {
            previousArea = newArea.copy()
            newArea = newArea.mutateAreaPart1()
        }

        return newArea.map { it.count { seat -> seat.isOccupied() } }.sum()
    }

    override fun partTwo(): Any {
        val area = input.readLines().map { it.toCharArray() }

        var previousArea = area.copy()
        var newArea = previousArea.mutateAreaPart2()

        while (!(previousArea isSameAs newArea)) {
            previousArea = newArea.copy()
            newArea = newArea.mutateAreaPart2()
        }

        return newArea.map { it.count { seat -> seat.isOccupied() } }.sum()
    }

    private fun List<CharArray>.mutateAreaPart1(): List<CharArray> {
        val boundaries = Boundaries(0, 0, this[0].size - 1, size - 1)
        val newArea = this.copy()

        for (y in 0..boundaries.maxY) {
            for (x in 0..boundaries.maxX) {
                val currentPos = Point(x, y)
                var surroundings = listOf<Char>()

                Directions.values().forEach { direction ->
                    val point = currentPos + direction.vector
                    if (point `in` boundaries)
                        surroundings = surroundings.plus(this.get(point))
                }

                if (newArea.get(currentPos).isFree() && surroundings.none { it.isOccupied() })
                    newArea.set('#', currentPos)

                if (newArea.get(currentPos).isOccupied() && surroundings.count { it.isOccupied() } >= 4)
                    newArea.set('L', currentPos)
            }
        }

        return newArea
    }

    private fun List<CharArray>.mutateAreaPart2(): List<CharArray> {
        val boundaries = Boundaries(0, 0, this[0].size - 1, size - 1)
        val newArea = this.copy()

        for (y in 0..boundaries.maxY) {
            for (x in 0..boundaries.maxX) {
                val currentPos = Point(x, y)
                var surroundings = listOf<Char>()

                Directions.values().forEach { direction ->
                    var point = currentPos + direction.vector

                    while (point `in` boundaries && this.get(point).isSeat().not())
                        point += direction.vector

                    if (point `in` boundaries && this.get(point).isSeat())
                        surroundings = surroundings.plus(this.get(point))
                }

                if (newArea.get(currentPos).isFree() && surroundings.none { it.isOccupied() })
                    newArea.set('#', currentPos)

                if (newArea.get(currentPos).isOccupied() && surroundings.count { it.isOccupied() } >= 5)
                    newArea.set('L', currentPos)
            }
        }

        return newArea
    }

    private fun Char.isSeat() = isFree() || isOccupied()
    private fun Char.isFree() = this == 'L'
    private fun Char.isOccupied() = this == '#'

    private fun List<CharArray>.copy() =
        this.map { it.clone() }.toList()

    private infix fun List<CharArray>.isSameAs(other: List<CharArray>) =
        this.zip(other).all { (x, y) -> x contentEquals y }

    private fun List<CharArray>.get(point: Point) =
        this[point.y][point.x]

    private fun List<CharArray>.set(value: Char, point: Point) {
        this[point.y][point.x] = value
    }
}

data class Boundaries(val minX: Int, val minY: Int, val maxX: Int, val maxY: Int)

data class Point(val x: Int, val y: Int) {
    infix operator fun plus(other: Point) =
        copy(x = x + other.x, y = y + other.y)

    infix fun `in`(boundaries: Boundaries) =
        (boundaries.minX..boundaries.maxX).contains(x) && (boundaries.minY..boundaries.maxY).contains(y)
}

enum class Directions(val vector: Point) {
    N(Point(0, -1)),
    NE(Point(1, -1)),
    E(Point(1, 0)),
    SE(Point(1, 1)),
    S(Point(0, 1)),
    SW(Point(-1, 1)),
    W(Point(-1, 0)),
    NW(Point(-1, -1))
}