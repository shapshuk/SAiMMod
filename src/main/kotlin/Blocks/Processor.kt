package Blocks

import Block
import Randomizer

class Processor(private val Pi : Float) : Block {

    override var state: Int = 0
    override val isAvailable get() = state == 0
    private var request: Request? = null

    override fun process(system: System, parentBlock: Block?) {
        if (state == 1) {
            if (!Randomizer.rollDice(Pi)) {
                system.passRequest(parentBlock ?: this, this.request!!)
                state = 0
            }
        }
    }

    override fun acceptRequest(system: System, request: Request) {
        if (isAvailable) {
            state = 1
            this.request = request

        } else throw Exception("Blocks.Processor not available")
    }

    override fun getState(): String {
        return state.toString()
    }

    override fun getRequestsCount(): Int {
        return state
    }
}