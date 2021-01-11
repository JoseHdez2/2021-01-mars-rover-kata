import mars_rover.Direction
import mars_rover.MarsRoverMovement
import mars_rover.MarsRoverService
import mars_rover.Position
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class MarsRoverServiceTest {

    @Test
    fun `illegal first argument (char) `() {
        assertThrows<IllegalArgumentException> {
            MarsRoverService.parseAndMove(arrayOf("A", "0", "NORTH", "F"))
        }
    }

    @Test
    fun `illegal second argument (blank)`() {
        assertThrows<IllegalArgumentException> {
            MarsRoverService.parseAndMove(arrayOf("0", "", "NORTH", "F"))
        }
    }

    @Test
    fun `illegal third argument`() {
        assertThrows<IllegalArgumentException> {
            MarsRoverService.parseAndMove(arrayOf("0", "0", "ONWARD", "F"))
        }
    }

    @Test
    fun `invalid command`() {
        assertThrows<InvalidCommandException> {
            MarsRoverService.parseAndMove(arrayOf("0", "0", "NORTH", "F F"))
        }
    }

    @Test
    fun `valid arguments once`() {
        val finalPos = MarsRoverService.parseAndMove(arrayOf("0", "0", "NORTH", "F"))
        kotlin.test.assertEquals(expected = "(0, 1) NORTH", actual = finalPos)
    }

    @Test
    fun `valid arguments thrice`() {
        val finalPos = MarsRoverService.parseAndMove(arrayOf("0", "0", "NORTH", "FFF"))
        kotlin.test.assertEquals(expected = "(0, 3) NORTH", actual = finalPos)
    }
}