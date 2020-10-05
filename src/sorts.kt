fun<T: RandomVariable> List<T>.selectionSort(): List<T> {
    val result = this.toMutableList()

    result.withIndex().forEach { (i, value) ->
        var localMin = value
        var localMinIndex = i
        result.slice(i..(this.size-1)).withIndex().forEach { (j, other) ->
            if (other < localMin) {
                localMin = other
                localMinIndex = i+j
            }
        }
        if (localMinIndex != i) {
            val t = result[i]
            result[i] = result[localMinIndex]
            result[localMinIndex] = t
        }
    }

    return result
}

fun<T: RandomVariable> List<T>.bubbleSort(): List<T> {
    val result = this.toMutableList()

    repeat(this.size) {
        result.indices.forEach { i ->
            if (i != 0) {
                if (result[i-1] > result[i]) {
                    val t = result[i]
                    result[i] = result[i-1]
                    result[i-1] = t
                }
            }
        }
    }

    return result
}

fun<T: RandomVariable> MutableList<T>.mergeSort(helper:MutableList<T> = this.toMutableList(), low:Int = 0, high : Int = size - 1) {
    if(low < high) {
        val middle:Int = (low+high)/2
        mergeSort(helper, low, middle)
        mergeSort(helper, middle+1, high)
        merge(helper, low, middle, high)
    }
}

fun<T: RandomVariable> MutableList<T>.merge (helper: MutableList<T>, low: Int, middle:Int, high: Int){
    for(i in low..high) helper[i] = this[i]

    var helperLeft = low
    var helperRight = middle + 1
    var current = low

    while (helperLeft <= middle && helperRight <= high) {
        if(helper[helperLeft] <= helper[helperRight]) {
            this[current] = helper[helperLeft]
            helperLeft++
        } else {
            this[current] = helper[helperRight]
            helperRight++
        }
        current++
    }

    val remaining = middle - helperLeft
    for (i in 0..remaining) {
        this[current + i] = helper[helperLeft + i]
    }
}

fun<T: RandomVariable> List<T>.mergeSort(): List<T> {
    val result = this.toMutableList()

    result.mergeSort()

    return result
}

fun<T: RandomVariable> List<T>.bubbleSort2(): List<T> {
    val result = this.toMutableList()

    var swaped = true

    while (swaped) {
        swaped = false
        result.indices.forEach { i ->
            if (i != 0) {
                if (result[i-1] > result[i]) {
                    val t = result[i]
                    result[i] = result[i-1]
                    result[i-1] = t
                    swaped = true
                }
            }
        }
    }

    return result
}

fun<T: RandomVariable> List<T>.stdLibSort(): List<T> = this.sortedBy { it.sample() }