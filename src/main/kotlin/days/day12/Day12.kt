package days

import days.day12.Action
import days.day12.ActionType
import days.day12.Directions
import days.day12.Waypoint
import java.io.File

class Day12(override val input: File) : Day {
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