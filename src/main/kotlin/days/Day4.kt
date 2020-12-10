package days

import java.io.File

class Day4(override val input: File) : Day {

    override fun partOne(): Any {
        val passports = parseLinesToPassport(input.readLines())
        return passports.count { simpleValidation(it) }
    }

    override fun partTwo(): Any {
        val passports = parseLinesToPassport(input.readLines())
        return passports.count { advancedValidation(it) }
    }

    private fun simpleValidation(passport: Map<String, String>): Boolean =
        passport.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))

    private fun advancedValidation(passport: Map<String, String>): Boolean {
        if (!simpleValidation(passport)) return false
        return passport.all { checkField(it.key, it.value) }
    }

    private fun checkField(key: String, value: String): Boolean {
        return when (key) {
            "byr" -> {
                val byr = value.toInt()
                byr in 1920..2002
            }
            "iyr" -> {
                val iyr = value.toInt()
                iyr in 2010..2020
            }
            "eyr" -> {
                val eyr = value.toInt()
                eyr in 2020..2030
            }
            "hgt" -> {
                val heightReg = Regex("([0-9]+)(in|cm)")
                if (heightReg.matches(value).not()) false
                else {
                    val (height, unit) = heightReg.matchEntire(value)!!
                        .destructured
                    when (unit) {
                        "in" -> height.toInt() in 59..76
                        "cm" -> height.toInt() in 150..193
                        else -> false
                    }
                }
            }
            "hcl" -> {
                val colorReg = Regex("#[a-f0-9]{6}")
                colorReg.matches(value)
            }
            "ecl" -> {
                listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
            }
            "pid" -> {
                if (value.length != 9) false
                else {
                    value.toCharArray()
                        .map { it.toString().toIntOrNull() }
                        .filterNotNull()
                        .count() == 9
                }
            }
            "cid" -> true
            else -> false
        }
    }

    private fun parseLinesToPassport(lines: List<String>): List<Map<String, String>> {
        val iterator = lines.iterator()

        val list = mutableListOf<Map<String, String>>()
        var passport = mutableMapOf<String, String>()

        while (iterator.hasNext()) {
            val line = iterator.next()

            if (line.isBlank()) {
                list.add(passport.toMutableMap())
                passport = mutableMapOf()
            } else {
                line.split(" ")
                    .forEach {
                        val kv = it.split(":")
                        passport[kv[0]] = kv[1]
                    }
            }
        }

        list.add(passport)
        return list
    }
}