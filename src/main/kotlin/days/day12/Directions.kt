package days.day12

enum class Directions(val degrees: Int) {
    N(0),
    E(90),
    S(180),
    W(270);

    fun rotate(value: Int): Directions {
        return if (value > 0) {
            values().first { it.degrees == ((this.degrees + value) % 360) }
        } else {
            values().first { it.degrees == ((this.degrees + value + 360) % 360) }
        }
    }
}