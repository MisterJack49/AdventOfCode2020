package days

import days.day8.Instruction
import days.day8.StateMachine
import java.io.File

class Day8(override val input: File) : Day {
    override fun partOne(): Any {
        val instructions = input.readLines()
            .parseInstructions()

        val stateMachine = StateMachine(instructions, 0)

        return stateMachine.run().result
    }

    override fun partTwo(): Any {
        val initialInstructions = input.readLines()
            .parseInstructions()

        val nopsToJmps = initialInstructions
            .filter { it.command == "nop" }
            .map {
                val index = initialInstructions.indexOf(it)
                initialInstructions.clone()
                    .toMutableList()
                    .apply { this[index] = it.copy(command = "nop") }
            }

        val jmpsToNops = initialInstructions
            .filter { it.command == "jmp" }
            .map {
                val index = initialInstructions.indexOf(it)
                initialInstructions.clone()
                    .toMutableList()
                    .apply { this[index] = it.copy(command = "nop") }
            }


        return nopsToJmps
            .plus(jmpsToNops)
            .map { instructions -> StateMachine(instructions).run() }
            .first { it.exitCode == 0 }
            .result
    }

    private fun List<String>.parseInstructions() =
        this.map { line ->
            line.split(" ")
                .let { (command, argument) ->
                    Instruction(command, argument.toInt())
                }
        }

    private fun List<Instruction>.clone() =
        map { it.copy() }.toList()

    private val Pair<Int, Int>.exitCode
        get() = first

    private val Pair<Int, Int>.result
        get() = second

}


