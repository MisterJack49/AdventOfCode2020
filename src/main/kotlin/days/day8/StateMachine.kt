package days.day8

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