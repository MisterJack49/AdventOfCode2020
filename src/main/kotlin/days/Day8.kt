package days

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

data class Instruction(val command: String, val argument: Int, var ran: Boolean = false)

data class StateMachine(val instructions: List<Instruction>, var accumulator: Int = 0) {
    fun run(index: Int = 0): Pair<Int, Int> {
        if (index == instructions.count()) return Pair(0, accumulator)

        val instruction = instructions[index]
        if (instruction.ran) return Pair(-1, accumulator)

        return when (instruction.command) {
            "jmp" -> {
                instruction.ran = true
                run(index + instruction.argument)
            }
            "acc" -> {
                accumulator += instruction.argument
                instruction.ran = true
                run(index + 1)
            }
            else -> {
                instruction.ran = true
                run(index + 1)
            }
        }
    }
}


