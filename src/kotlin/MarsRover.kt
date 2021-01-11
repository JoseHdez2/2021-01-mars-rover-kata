package mars_rover

import kotlin.test.assertEquals

enum class Direction { NORTH, SOUTH, EAST, WEST }

data class Position(val x : Int, val y : Int, val direction: Direction)

enum class Command { FORWARD }

val charToCommand = hashMapOf("F" to Command.FORWARD)



class MarsRoverMovement {
    companion object {
        fun move(initialPos: Position, commands: String) : Position {
            return initialPos // TODO
        }
    }
}

fun main(args: Array<String>) {
    val res = MarsRoverMovement.move(Position(0, 0, Direction.NORTH), "F")
    assertEquals(Position(0, 1, Direction.NORTH), res)
}

