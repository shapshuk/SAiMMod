import Blocks.Processor
import Blocks.Queue
import Blocks.RandSource
import Blocks.System


fun main() {

    val totalRequests = 100000

    val Pi1 = 0.55f
    val Pi2 = 0.6f
    val Pi3 = 0.75f

    val blockList : List<Block> = listOf(
        Blocks.Source(),
        Blocks.Processor(Pi1),
        Blocks.ProcessorPair(Blocks.Processor(Pi2), Blocks.Processor(Pi3))
    )

    val system = System(blockList, totalRequests)
    system.run()
}