package days.day7

data class Bag(val type: String, val inventory: List<Capacity>) {
    fun canHold(bag: String) =
        inventory.any { it.type == bag }
}