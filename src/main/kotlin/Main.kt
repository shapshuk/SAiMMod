import Blocks.Processor
import Blocks.Queue
import Blocks.RandSource
import Blocks.System


fun main() {

    // Variant 41
    val Pi1 = 0.55f
    val Pi2 = 0.6f
    val Pi3 = 0.75f


    val blockList : List<Block> = listOf(
        Blocks.Source(),
        Blocks.Processor(Pi1),
        Blocks.ProcessorPair(Blocks.Processor(Pi2), Blocks.Processor(Pi3))
    )

    // var 11
//    val Pi1 = 0.55f
//    val Pi2 = 0.7f
//
//    val blockList : List<Block> = listOf(
//        Blocks.Source(),
//        Blocks.Queue(2),
//        Blocks.Processor(Pi1),
//        Blocks.Processor(Pi2)
//    )

    // Var 26
//    val p = 0.7f
//    val Pi1 = 0.7f
//    val Pi2 = 0.75f
//    val queueLength = 1
//
//    val blockList : List<Block> = listOf(
//        RandSource(p),
//        Processor(Pi1),
//        Queue(queueLength),
//        Processor(Pi2)
//    )

    // var 28
//    val p = 0.75f
//    val Pi1 = 0.7f
//    val Pi2 = 0.65f
//    val queueLength = 2
//
//    val blockList : List<Block> = listOf(
//        Blocks.RandSource(p),
//        Blocks.Processor(Pi1),
//        Blocks.Queue(queueLength),
//        Blocks.Processor(Pi2)
//    )

    //  var 10
//    val p = 0.5f
//    val Pi1 = 0.7f
//    val Pi2 = 0.6f
//    val queueLength = 1
//
//    val blockList : List<Block> = listOf(
//        Blocks.RandSource(p),
//        Blocks.Queue(queueLength),
//        Blocks.Processor(Pi1),
//        Blocks.Processor(Pi2)
//    )

    //var 3
//    val Pi1 = 0.5f
//    val Pi2 = 0.8f
//    val queueLength = 2
//
//    val blockList : List<Block> = listOf(
//        Blocks.Source(),
//        Blocks.Queue(queueLength),
//        Blocks.ProcessorPair(
//            Blocks.Processor(Pi1),
//            Blocks.Processor(Pi2))
//    )

    // var 35



    val system = System(blockList)
    system.run()
}