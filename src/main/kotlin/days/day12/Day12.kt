package days

import days.day12.Action
import days.day12.ActionType
import days.day12.Directions
import days.day12.Waypoint
import java.io.File

class Day12(override val input: File) : Day {

    /**
    Action N means to move north by the given value.
    Action S means to move south by the given value.
    Action E means to move east by the given value.
    Action W means to move west by the given value.
    Action L means to turn left the given number of degrees.
    Action R means to turn right the given number of degrees.
    Action F means to move forward by the given value in the direction the ship is currently facing.
     */
    override fun partOne(): Any {
        var boat =
            Waypoint(
                Directions.E,
                mutableMapOf(Directions.N to 0, Directions.E to 0, Directions.S to 0, Directions.W to 0)
            )
        input.readLines().parseActions()
            .forEach { action ->

                boat = when (action.type) {
                    ActionType.N, ActionType.E, ActionType.S, ActionType.W -> {
                        boat.updatePosition(action.type.toDirection(), action.value)
                    }
                    ActionType.L -> {
                        boat.changeFacingDirection(-action.value)
                    }
                    ActionType.R -> {
                        boat.changeFacingDirection(action.value)
                    }
                    ActionType.F -> {
                        boat.updatePosition(boat.facing, action.value)
                    }
                }
            }

        return boat.getDistance()
    }

    override fun partTwo(): Any {
        var boat =
            Waypoint(
                Directions.E,
                mutableMapOf(Directions.N to 0, Directions.E to 0, Directions.S to 0, Directions.W to 0)
            )

        var waypoint =
            Waypoint(
                Directions.E,
                mutableMapOf(Directions.N to 1, Directions.E to 10, Directions.S to 0, Directions.W to 0)
            )
        input.readLines().parseActions()
            .forEach { action ->
                when (action.type) {
                    ActionType.N, ActionType.E, ActionType.S, ActionType.W -> {
                        waypoint = waypoint.updatePosition(action.type.toDirection(), action.value)
                    }
                    ActionType.L -> {
                        waypoint = waypoint.rotatePosition(-action.value)
                    }
                    ActionType.R -> {
                        waypoint = waypoint.rotatePosition(action.value)
                    }
                    ActionType.F -> {
                        boat += waypoint * action.value
                    }
                }
            }

        return boat.getDistance()
    }

    private fun List<String>.parseActions(): List<Action> {
        val regex = Regex("^([A-Z])([0-9]+)$")
        return map {
            regex.matchEntire(it)!!.destructured.let { (type, value) ->
                Action(
                    ActionType.valueOf(type),
                    value.toInt()
                )
            }
        }.toList()
    }

}