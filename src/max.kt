import kotlin.time.measureTime

fun<T: RandomVariable> List<T>.max(): T {
    var localMax: T? = null
    this.forEach {
        if (localMax == null) {
            localMax = it
        }
        if (it > localMax!!) {
            localMax = it
        }
    }
    return localMax!!
}

fun<T: RandomVariable> List<T>.singleBrackets(): T {
    if (this.size == 1) {
        return this[0]
    }

    val middle = (this.size) / 2
    val max1 = this.subList(0, middle).singleBrackets()
    val max2 = this.subList(middle, this.size).singleBrackets()

    return if (max1 > max2)
        max1
    else
        max2
}
