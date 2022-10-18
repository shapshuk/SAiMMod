import Blocks.Request
import Blocks.System

interface Block {

    val state : Int
    val isAvailable: Boolean

    fun process(system: System, parentBlock: Block? = null)

    fun acceptRequest(system: System, request: Request)

    fun getState() : String

    fun getRequestsCount() : Int

}