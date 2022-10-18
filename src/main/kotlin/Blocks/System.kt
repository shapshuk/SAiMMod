package Blocks

import Block

class System(private val blockList: List<Block>, private val totalRequests : Int = 100) {


    private var processedRequests : Int = 0
    private var deniedRequests : Int = 0

    private var totalRequestLifetime = 0

    var tactCounter = 0

    var queueTotalTime = 0

    private val statesMap : MutableMap<String, Int> = mutableMapOf()

    fun getNext(currentBlock: Block) : Block {
        return blockList[blockList.indexOf(currentBlock) + 1]
    }

    fun passRequest(block: Block, request: Request) {

        val currentBlock = blockList.indexOf(block)

        if (currentBlock == blockList.size - 1) {
            processedRequests += 1
            totalRequestLifetime += tactCounter - request.timestamp
        }
        else {
            val nextBlock : Block = blockList[currentBlock + 1]

            if (nextBlock.isAvailable) {
                nextBlock.acceptRequest(system = this, request = request)
            } else {
                deniedRequests += 1
                totalRequestLifetime += tactCounter - request.timestamp
            }
        }
    }

    private fun getSystemState() : String {
        var sysState = ""
        for (block in blockList) {
            sysState += (block.getState())
        }
        return sysState
    }



    fun run() {


        var totalSystemRequests = 0
        var totalQueueLength = 0
        var totalProcessorRequests = 0



        while (deniedRequests + processedRequests < totalRequests) {


            val sysState = getSystemState()

            if (sysState !in statesMap) {
                statesMap[sysState] = 1
            } else statesMap[sysState] = statesMap[sysState]!! + 1

//            print(getSystemState() + " -> ")

            // var 41 only
            for (block in blockList) {
                totalSystemRequests += block.getRequestsCount()

                if (block is Queue) {
                    totalQueueLength += block.getRequestsCount()
                }

                if (block is Processor || block is ProcessorPair) {
                    totalProcessorRequests += block.getRequestsCount()
                }

            }


//            print(getSystemState() + " -> ")

            // system handler
            for (i in blockList.size downTo 1) {
                blockList[i - 1].process(this)
            }
//            println(getSystemState())

            tactCounter += 1
        }

        println("Processed requests: $processedRequests")
        println("Denied requests: $deniedRequests")

//        println(statesMap)

        for (pair in statesMap) {
//            println("P${pair.key} - ${"%.5f".format(pair.value / tactCounter.toFloat())} --- ${pair.value}")

            println("P${pair.key} - ${"%.5f".format(pair.value / tactCounter.toFloat())}")
        }


        println()


        println("Total tact count = $tactCounter")

        println("Deny probability (Pотк) = ${1 - processedRequests / totalRequests.toFloat()}")
        // Probability of blocking
        println("Average queue length (Lоч) = ${totalQueueLength / tactCounter.toFloat()}")
        println("Average number of requests in system (Lc) = ${totalSystemRequests / tactCounter.toFloat()}")
        println("Request processing probability (Q) = ${processedRequests / totalRequests.toFloat()}")
        println("Absolute throughput (A) = ${processedRequests / tactCounter.toFloat()}")
        println("Average time in queue (Wоч) = ${queueTotalTime / totalRequests.toFloat()}")
        println("Average request lifetime (Wc) = ${totalRequestLifetime / totalRequests.toFloat()}")
        println("Processor occupancy probability (K) = ${totalProcessorRequests / tactCounter.toFloat()}")

    }
}