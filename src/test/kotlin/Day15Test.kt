import days.day15.Day15
import org.amshove.kluent.`should be equal to`
import org.junit.Test

class Day15Test {

    @Test
    fun `2020th value for 0,3,6`(){
        Day15.getValueAtTurn(listOf(0,3,6), 2020) `should be equal to` 436
    }

    @Test
    fun `2020th value for 1,3,2`(){
        Day15.getValueAtTurn(listOf(1,3,2), 2020) `should be equal to` 1
    }

    @Test
    fun `2020th value for 2,1,3`(){
        Day15.getValueAtTurn(listOf(2,1,3), 2020) `should be equal to` 10
    }

    @Test
    fun `2020th value for 1,2,3`(){
        Day15.getValueAtTurn(listOf(1,2,3), 2020) `should be equal to` 27
    }

    @Test
    fun `2020th value for 2,3,1`(){
        Day15.getValueAtTurn(listOf(2,3,1), 2020) `should be equal to` 78
    }

    @Test
    fun `2020th value for 3,2,1`(){
        Day15.getValueAtTurn(listOf(3,2,1), 2020) `should be equal to` 438
    }

    @Test
    fun `2020th value for 3,1,2`(){
        Day15.getValueAtTurn(listOf(3,1,2), 2020) `should be equal to` 1836
    }

    @Test
    fun `30000000th value for 0,3,6`(){
        Day15.getValueAtTurn(listOf(0,3,6), 30000000) `should be equal to` 175594
    }

    @Test
    fun `30000000th value for 1,3,2`(){
        Day15.getValueAtTurn(listOf(1,3,2), 30000000) `should be equal to` 2578
    }

    @Test
    fun `30000000th value for 2,1,3`(){
        Day15.getValueAtTurn(listOf(2,1,3), 30000000) `should be equal to` 3544142
    }

}