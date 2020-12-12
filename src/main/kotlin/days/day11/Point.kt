package days.day11

data class Point(val x: Int, val y: Int) {
    infix operator fun plus(other: Point) =
        copy(x = x + other.x, y = y + other.y)

    infix fun `in`(boundaries: Boundaries) =
        (boundaries.minX..boundaries.maxX).contains(x) && (boundaries.minY..boundaries.maxY).contains(y)
}