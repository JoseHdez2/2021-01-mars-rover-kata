import com.example.Direction
import com.example.MarsRoverMovement
import com.example.MarsRoverService
import com.example.Position
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

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
        assertEquals(expected = "(0, 1) NORTH", actual = finalPos)
    }

    @Test
    fun `valid arguments thrice`() {
        val finalPos = MarsRoverService.parseAndMove(arrayOf("0", "0", "NORTH", "FFF"))
        assertEquals(expected = "(0, 3) NORTH", actual = finalPos)
    }
}