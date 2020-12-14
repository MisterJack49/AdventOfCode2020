package days.day14

import days.Day
import java.io.File
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*

class Day14(override val input: File) : Day {
    override fun partOne(): Any {
        var mask = ""
        val memory = mutableMapOf<Int, Long>()

        val maskRegex = Regex("^mask = ([01X]+)$")
        val memoryRegex = Regex("^mem\\[([0-9]+)] = ([0-9]+)$")

        input.readLines()
            .forEach { line ->
                if (maskRegex.matches(line)) {
                    mask = maskRegex.matchEntire(line)!!.destructured.component1()
                } else {
                    memoryRegex.matchEntire(line)!!.destructured
                        .let { (address, value) ->
                            val bits = value.toBinary()
                            bits.fillTo(36)
                            mask.withIndex().forEach {
                                when (it.value) {
                                    '1' -> bits[it.index] = '1'
                                    '0' -> bits[it.index] = '0'
                                }
                            }
                            memory[address.toInt()] = bits.joinToString("").toLong(2)
                        }
                }
            }

        return memory.values.sum()
    }

    override fun partTwo(): Any {
        var mask = ""
        val memory = mutableMapOf<Long, Long>()

        val maskRegex = Regex("^mask = ([01X]+)$")
        val memoryRegex = Regex("^mem\\[([0-9]+)] = ([0-9]+)$")

        input.readLines()
            .forEach { line ->
                if (maskRegex.matches(line)) {
                    mask = maskRegex.matchEntire(line)!!.destructured.component1()
                } else {
                    memoryRegex.matchEntire(line)!!.destructured
                        .let { (address, value) ->
                            val bits = address.toBinary()
                            bits.fillTo(36)

                            mask.withIndex().forEach {
                                when (it.value) {
                                    '1' -> bits[it.index] = '1'
                                    'X' -> bits[it.index] = 'X'
                                }
                            }

                            getAllAddresses(bits.joinToString(""))
                                .forEach {
                                    memory[it.toLong(2)] = value.toLong()
                                }

                        }
                }
            }

        return memory.values.sum()
    }

    private fun String.toBinary() = this.toLong().toString(2).toMutableList()
    private fun String.copy() = this.toString()

    private fun MutableList<Char>.fillTo(size: Int) {
        val diff = size - this.count()
        for (i in 0 until diff) this.add(0, '0')
    }

    private fun getAllAddresses(address: String): List<String> {
        if (address.none { it == 'X' }) return listOf(address)
        val address0 = address.copy().replaceFirst('X', '0')
        val address1 = address.copy().replaceFirst('X', '1')
        return listOf(getAllAddresses(address0), getAllAddresses(address1)).flatten()
    }

}