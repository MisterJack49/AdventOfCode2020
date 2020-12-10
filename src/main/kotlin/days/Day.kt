package days

import java.io.File

interface Day {
    val input: File
    fun partOne(): Any
    fun partTwo(): Any
}