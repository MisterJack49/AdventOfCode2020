package days

import java.io.File

class Day9(override val input: File) : Day {
    override fun partOne(): Any {
        val message = input.readLines().map { it.toLong() }
        return findWeakness(message, 25)
    }

    override fun partTwo(): Any {
        val message = input.readLines().map { it.toLong() }
        val weakness = findWeakness(message, 25)
        return getMinMaxSum(findWeaknessSum(message, weakness))
    }

    fun findWeakness(fullMessage: List<Long>, preambleLength: Int): Long {
        var weakness = 0L
        for (i in preambleLength until fullMessage.count()) {
            val value = fullMessage[i]
            val previous = fullMessage.drop(i - preambleLength).take(preambleLength)

            if (previous.none { previous.contains(value - it) }) {
                weakness = value
                break
            } else continue
        }

        return weakness
    }

    fun findWeaknessSum(message: List<Long>, weakness: Long): List<Long> {
        var sumSize = 2

        while (true) {
            for (i in 0 until message.count()) {
                val list = message.drop(i).take(sumSize)
                if (list.sum() == weakness)
                    return list
            }
            sumSize += 1
        }
    }

    fun getMinMaxSum(list: List<Long>) =
        list.sorted().let { it.first() + it.last() }

}