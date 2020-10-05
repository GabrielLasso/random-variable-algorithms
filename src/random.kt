import java.util.*

private val rng = Random()

interface RandomVariable {
    fun sample(): Double

    operator fun compareTo(other: RandomVariable) = sample().compareTo(other.sample())
}

data class Gaussian(val mean: Double, val stdDev: Double) : RandomVariable {

    override fun sample(): Double {
        return rng.nextGaussian() * stdDev + mean
    }
}
