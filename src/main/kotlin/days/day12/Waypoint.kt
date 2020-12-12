package days.day12

import kotlin.math.abs

data class Waypoint(val facing: Directions, val positions: Map<Directions, Int>) {
    fun updatePosition(direction: Directions, value: Int): Waypoint {
        return copy(positions = positions.map { it.key to if (it.key == direction) it.value + value else it.value }
            .toMap())
    }

    fun changeFacingDirection(value: Int): Waypoint {
        return copy(facing = facing.rotate(value))
    }

    fun rotatePosition(value: Int): Waypoint {
        return copy(positions = positions.map { it.key.rotate(value) to it.value }.toMap())
    }

    fun getDistance() =
        abs(positions[Directions.N]!! - positions[Directions.S]!!) + abs(positions[Directions.E]!! - positions[Directions.W]!!)

    infix operator fun plus(other: Waypoint): Waypoint {
        return copy(positions = positions.map { it.key to other.positions[it.key]!! + it.value }.toMap())
    }

    infix operator fun times(value: Int): Waypoint {
        return copy(positions = positions.map { it.key to value * it.value }.toMap())
    }

    override fun toString(): String {
        return "$facing, [${positions.map { "${it.key}:${it.value}" }.joinToString()}]"
    }
}