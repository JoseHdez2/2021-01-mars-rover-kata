import mars_rover.Direction
import mars_rover.MarsRoverMovement
import mars_rover.Position
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MarsRoverMovementTest {

    private fun testMove(initialPos: Position, command: String, expectedPos: Position) {
        val finalPos = MarsRoverMovement.move(initialPos, command)
        kotlin.test.assertEquals(expected = expectedPos, actual = finalPos)
    }

    @Test
    fun `move forward once`() =
        testMove(
            initialPos = Position(0,0, Direction.NORTH),
            command = "F",
            expectedPos = Position(0, 1, Direction.NORTH)
        )

    @Test
    fun `move forward twice`() =
        testMove(
            initialPos = Position(0,0, Direction.NORTH),
            command = "FF",
            expectedPos = Position(0, 2, Direction.NORTH)
        )

    @Test
    fun `move forward thrice`() =
        testMove(
            initialPos = Position(0,0, Direction.NORTH),
            command = "FFF",
            expectedPos = Position(0, 3, Direction.NORTH)
        )

    @Test
    fun `empty and blank commands throw EmptyCommand exception`() =
        listOf("", "   ", "        ").forEach { command ->
            assertThrows<BlankCommandException> {
                MarsRoverMovement.move(initialPos = Position(0, 0, Direction.NORTH), commandStr = command)
            }
        }

    @Test
    fun `illegal commands throw InvalidCommand exception`() =
        listOf("F F", "U", "DW", "HELLO").forEach { command ->
            assertThrows<InvalidCommandException> {
                MarsRoverMovement.move(initialPos = Position(0, 0, Direction.NORTH), commandStr = command)
            }
        }
}