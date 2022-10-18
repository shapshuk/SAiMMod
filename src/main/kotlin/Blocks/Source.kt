package Blocks

import Block

class Source() : Block {

    override val isAvailable: Boolean = true
    override var state: Int = 2

    override fun process(system: System, parentBlock: Block?) {
        if (state == 1) {
            system.passRequest(this, Request(system.tactCounter))
            state = 2
        }
        else {
            state = 1
        }
    }

    override fun acceptRequest(system: System, request: Request) {
        // pass
    }

    override fun getState(): String {
        return state.toString()
    }

    override fun getRequestsCount(): Int {
        return 0
    }

}