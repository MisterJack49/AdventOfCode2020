package days

import java.io.File

class Day7(override val input: File) : Day {
    override fun partOne(): Any {
        val policies = input.readLines()
            .getBagsPolicies()

        val holders = policies.filter { it.canHold("shiny gold") }.map { it.key }.toMutableList()

        var tmp = holders.toList()
        while (tmp.isEmpty().not()) {
            tmp = tmp.map { bag ->
                policies
                    .filter { it.canHold(bag) }
                    .map { it.key }
            }.flatten()
                .distinct()

            holders.addAll(tmp)
        }

        return holders.distinct().count()
    }

    override fun partTwo(): Any {
        val policies = input.readLines()
            .getBagsPolicies()

        val shinyBag = policies.getBag("shiny gold")

        return getBagCount(policies, shinyBag)
    }

    private fun getBagCount(
        policies: Map<String, List<Pair<Int, String>>>,
        bag: Map.Entry<String, List<Pair<Int, String>>>
    ): Int {
        if (bag.holds().isEmpty()) return 0
        return bag
            .holds()
            .map { it.quantity() + it.quantity() * getBagCount(policies, policies.getBag(it.bagType())) }
            .sum()
    }

    private fun Map.Entry<String, List<Pair<Int, String>>>.canHold(bag: String) =
        this.holds().any { it.second == bag }

    private fun Map.Entry<String, List<Pair<Int, String>>>.holds() =
        this.value

    private fun Map<String, List<Pair<Int, String>>>.getBag(type: String) =
        this.entries.first { it.key == type }

    private fun Pair<Int, String>.quantity() = this.first
    private fun Pair<Int, String>.bagType() = this.second

    private fun List<String>.getBagsPolicies(): Map<String, List<Pair<Int, String>>> {
        return this.map { policy ->
            policy.split(" contain")
                .let {
                    Pair(
                        it[0].replace(" bags", ""),
                        it[1].replace(" bag", "")
                            .replace(" no others", "")
                            .replace(".", "")
                            .split(",")
                            .filter { entry -> entry.isBlank().not() }
                            .map { bags ->
                                val regex = Regex("^ ([0-9]) (.*)$")
                                regex.matchEntire(bags)!!.destructured
                                    .let { (number, type) -> number.toInt() to type.removeSuffix("s") }
                            }
                    )
                }
        }.toMap()
    }
}