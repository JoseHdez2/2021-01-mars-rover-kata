package mars_rover

import BlankCommandException
import InvalidCommandException
import java.lang.IllegalArgumentException

enum class Direction { NORTH, SOUTH, EAST, WEST }

data class Position(val x : Int, val y : Int, val direction: Direction)

enum class Command { FORWARD }

val charToCommand = hashMapOf(
    'F' to Command.FORWARD
)

class MarsRoverMovement {
    companion object {
        fun move(initialPos: Position, commandStr: String) : Position {
            if(commandStr.isBlank()){
                throw BlankCommandException("Command [${commandStr}] is blank!")
            }
            val cmdList = commandStr.map { charToCommand[it] ?:
                throw InvalidCommandException("Command [${commandStr}] contains illegal character [${it}] !") }
            var finalPos = initialPos
            for (cmd : Command in cmdList) when(cmd) {
                Command.FORWARD -> finalPos = forward(finalPos)
            }
            return finalPos
        }

        private fun forward(position: Position) : Position {
            return when(position.direction) {
                Direction.NORTH -> position.let { it.copy(y = it.y + 1) }
                Direction.SOUTH -> position.let { it.copy(y = it.y - 1) }
                Direction.EAST -> position.let { it.copy(x = it.x + 1) }
                Direction.WEST -> position.let { it.copy(y = it.y - 1) }
            }
        }
    }
}

class MarsRoverService {
    companion object {
        fun parseAndMove(args: Array<String>) : String{
            if(args.size != 4){
                "Arguments: x, y, dir, commandStr.".let { throw Exception(it) }
            }
            val x = args[0].toIntOrNull() ?:
                throw IllegalArgumentException("Invalid first argument (x)! Expected an Int.")
            val y = args[1].toIntOrNull() ?:
                throw IllegalArgumentException("Invalid second argument (y)! Expected an Int.")
            val dir = Direction.values().find { it.name == args[2]} ?:
                throw IllegalArgumentException("Invalid third argument (dir)! Expected one of: ${Direction.values().map { it.name }}.")
            val commandStr = args[3]
            val pos = MarsRoverMovement.move(Position(x, y, dir), commandStr)
            return pos.let { "(${it.x}, ${it.y}) ${it.direction}" }
        }
    }
}

fun main(args: Array<String>) : String {
    return MarsRoverService.parseAndMove(args)
}

