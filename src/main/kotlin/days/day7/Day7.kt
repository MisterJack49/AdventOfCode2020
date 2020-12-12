package days

import days.day7.Bag
import days.day7.Capacity
import java.io.File

class Day7(override val input: File) : Day {
    override fun partOne(): Any {
        val policies = input.readLines()
            .getBagsPolicies()

        val holders = policies.filter { it.canHold("shiny gold") }.toMutableList()

        var tmp = holders
        while (tmp.isEmpty().not()) {
            tmp = tmp.map { bag ->
                policies
                    .filter { it.canHold(bag.type) }
            }.flatten()
                .distinct()
                .toMutableList()

            holders.addAll(tmp)
        }

        return holders.distinct().count()
    }

    override fun partTwo(): Any {
        val policies = input.readLines()
            .getBagsPolicies()

        val shinyBag = policies.getBag("shiny gold")

        return policies.getAllNecessaryBagsFor(shinyBag)
    }

    private fun List<Bag>.getAllNecessaryBagsFor(
        bag: Bag
    ): Int {
        if (bag.inventory.isEmpty()) return 0
        return bag
            .inventory
            .map { it.quantity + it.quantity * getAllNecessaryBagsFor(getBag(it.type)) }
            .sum()
    }

    private fun List<Bag>.getBag(type: String) =
        first { it.type == type }

    private fun List<String>.getBagsPolicies(): List<Bag> {
        return this.map { policy ->
            policy.split(" contain")
                .let {
                    Bag(
                        it[0].replace(" bags", ""),
                        it[1].replace(" bag", "")
                            .replace(" no others", "")
                            .replace(".", "")
                            .split(",")
                            .filter { entry -> entry.isBlank().not() }
                            .map { bags ->
                                val regex = Regex("^ ([0-9]) (.*)$")
                                regex.matchEntire(bags)!!.destructured
                                    .let { (number, type) -> Capacity(number.toInt(), type.removeSuffix("s")) }
                            }
                    )
                }
        }
    }
}