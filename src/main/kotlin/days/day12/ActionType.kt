package days.day12

enum class ActionType {
    N, S, E, W, L, R, F;

    fun toDirection() =
        when (this) {
            N -> Directions.N
            E -> Directions.E
            S -> Directions.S
            W -> Directions.W
            else -> throw IllegalArgumentException("Not a valid direction")
        }
}