package days.day11

enum class Directions(val vector: Point) {
    N(Point(0, -1)),
    NE(Point(1, -1)),
    E(Point(1, 0)),
    SE(Point(1, 1)),
    S(Point(0, 1)),
    SW(Point(-1, 1)),
    W(Point(-1, 0)),
    NW(Point(-1, -1))
}