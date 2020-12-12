package days.day2

import days.Day
import java.io.File

class Day2(override val input: File) : Day {
    override fun partOne(): Int {
       return input.readLines()
            .map { parseInput(it) }
            .map { validate(it) }
            .count { it }
    }

    override fun partTwo(): Int {
        return input.readLines()
            .map { parseInput(it) }
            .map { validate2(it) }
            .count { it }
    }

    private fun parseInput(line: String): Triple<List<Int>, Char, String> {
        val arr = line.split(" ")
        val minmax = arr[0].split("-").map { it.toInt() }
        val letter = arr[1].toCharArray()[0]
        val password = arr[2]

        return Triple(minmax, letter, password)
    }

    private fun validate(input: Triple<List<Int>, Char, String>): Boolean {
        val (minmax, letter, password) = input

        val count = password.toCharArray().count { it == letter }
        return count in minmax[0]..minmax[1]
    }

    private fun validate2(input: Triple<List<Int>, Char, String>): Boolean {
        val (positions, letter, password) = input

        val passArr = password.toCharArray()
        return (passArr[positions[0]-1] == letter) xor (passArr[positions[1]-1] == letter)
    }
}