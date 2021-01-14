

/**
 * Thrown when a command is blank or empty.
 * @sample "", "   ", "        "
 */
class BlankCommandException(msg: String) : Exception(msg)

/**
 * Thrown when an unknown command character is encountered.
 * @sample "F F", "U", "DW", "HELLO"
 */
class InvalidCommandException(msg: String) : Exception(msg)