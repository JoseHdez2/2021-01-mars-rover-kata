package mars_rover

import BlankCommandException
import InvalidCommandException
import kotlin.test.assertEquals

enum class Direction { NORTH, SOUTH, EAST, WEST }

data class Position(val x : Int, val y : Int, val direction: Direction)

enum class Command { FORWARD }

val charToCommand = hashMapOf(
    "F" to Command.FORWARD
)

class MarsRoverMovement {
    companion object {
        fun move(initialPos: Position, commandStr: String) : Position {
            if(commandStr.isBlank()){
                throw BlankCommandException("Command [${commandStr}] is blank!")
            }
            val cmdList = commandStr.map { charToCommand.get(it) ?: throw InvalidCommandException("Command [${commandStr}] contains illegal character [${it}] !") }
            var finalPos = initialPos
            for (cmd : Command in cmdList) when(cmd) {
                Command.FORWARD -> finalPos = forward(finalPos)
            }
            return finalPos
        }

        fun forward(position: Position) : Position {
            return when(position.direction) {
                Direction.NORTH -> position.let { it.copy(y = it.y + 1) }
                Direction.SOUTH -> position.let { it.copy(y = it.y - 1) }
                Direction.EAST -> position.let { it.copy(x = it.x + 1) }
                Direction.WEST -> position.let { it.copy(y = it.y - 1) }
            }
        }
    }
}

fun main(args: Array<String>) {
    val res = MarsRoverMovement.move(Position(0, 0, Direction.NORTH), "F")
    assertEquals(Position(0, 1, Direction.NORTH), res)
}

