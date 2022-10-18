package Blocks

import Block
import Randomizer

class RandSource(private val p : Float) : Block {

    override val isAvailable: Boolean = true
    override var state: Int = 0

    override fun process(system: System, parentBlock: Block?) {
        if (!Randomizer.rollDice(p)) {
            system.passRequest(parentBlock ?: this, Request(system.tactCounter))
        }
    }

    override fun acceptRequest(system: System, request: Request) {
        // pass
    }

    override fun getState(): String {
        return ""
    }

    override fun getRequestsCount(): Int {
        return 0
    }

}