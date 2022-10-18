package Blocks

import Block

class ProcessorPair(private val processorA: Processor,
                    private val processorB: Processor
) : Block {

    override val state get() = processorA.state * 10 + processorB.state // state A and B concatenation
    override val isAvailable get() = processorA.isAvailable || processorB.isAvailable


    override fun process(system: System, parentBlock: Block?) {
        processorA.process(system, this)
        processorB.process(system, this)
    }

    override fun acceptRequest(system: System, request: Request) {
        if (processorA.isAvailable) {
            processorA.acceptRequest(system, request)
        } else {
            processorB.acceptRequest(system, request)
        }
    }

    override fun getState(): String {
        return processorA.state.toString() + processorB.state.toString()
    }

    override fun getRequestsCount(): Int {
        return processorA.getRequestsCount() + processorB.getRequestsCount()
    }
}