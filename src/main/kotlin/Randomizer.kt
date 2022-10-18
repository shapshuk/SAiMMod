import java.util.*

object Randomizer {
    private val random = Random()
    fun rollDice(probability: Float) : Boolean {
        return random.nextFloat() < probability
    }
}