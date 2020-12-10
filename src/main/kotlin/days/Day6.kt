package days

import java.io.File

class Day6(override val input: File) : Day {
    override fun partOne(): Any {
        return input.readLines()
            .responsesByGroup()
            .map { group -> group.flattenResponses() }
            .map { groupResponses -> groupResponses.distinct() }
            .sumBy { distinctResponses -> distinctResponses.count() }
    }

    override fun partTwo(): Any {
        return input.readLines()
            .responsesByGroup()
            .map { group ->
                group.flattenResponses()
                    .groupBy { responseType -> responseType }
                    .map { (_, responsesOfType) -> responsesOfType.count() }
                    .filter { count -> count == group.count() }
                    .count()
            }
            .sum()
    }

    private fun List<List<Char>>.flattenResponses() =
        this.fold(mutableListOf<Char>(), { acc, list -> acc.addAll(list); acc })

    private fun List<String>.responsesByGroup(): List<List<List<Char>>> {
        val iterator = this.iterator()

        val groups = mutableListOf<List<List<Char>>>()
        var forms = mutableListOf<List<Char>>()

        while (iterator.hasNext()) {
            val line = iterator.next()

            if (line.isBlank()) {
                groups.add(forms)
                forms = mutableListOf()
            } else {
                forms.add(line.toCharArray().toMutableList())
            }
        }

        groups.add(forms)
        return groups
    }
}