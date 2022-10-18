package Blocks

import Block
import java.util.*

class Queue(private val length: Int) : Block {

    override val state: Int get() = requestQueue.size
    override val isAvailable: Boolean get() = requestQueue.size < length
    private val requestQueue: java.util.Queue<Pair<Request, Int>> = LinkedList()

    override fun process(system: System, parentBlock: Block?) {
        while (system.getNext(this).isAvailable && state > 0) {
            val pair = requestQueue.remove()
            system.queueTotalTime += system.tactCounter - pair.second
            system.passRequest(this, pair.first)
        }
    }

    override fun acceptRequest(system: System, request : Request) {
        if (system.getNext(this).isAvailable) {
            system.passRequest(this, request)
        } else if (isAvailable) {
            requestQueue.add(Pair(request, system.tactCounter))
        } else throw Exception("Blocks.Processor not available")
    }

    override fun getState(): String {
        return state.toString()
    }

    override fun getRequestsCount(): Int {
        return state
    }
}