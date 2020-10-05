import java.io.File
import kotlin.math.absoluteValue

val meanDistribution = Gaussian(0.0, 10.0)
val stdDevDistribution = Gaussian(0.0, 1.0)
fun experimentSort(agents: Int): Triple<Int, Int, Int> {
    val l = (1..agents).map { Gaussian(meanDistribution.sample(), stdDevDistribution.sample().absoluteValue) }.toList()
    val selectionSorted = l.selectionSort()
    val mergeSorted = l.mergeSort()
    val bubbleSorted = l.bubbleSort()

    val selectDisorder = selectionSorted.map { it.mean }.disorder()
    val mergeDisorder = mergeSorted.map { it.mean }.disorder()
    val bubbleDisorder = bubbleSorted.map { it.mean }.disorder()

    return Triple(selectDisorder, mergeDisorder, bubbleDisorder)
}

fun multipleExperimentsSort(agents: Int, times: Int): Triple<Double, Double, Double> {
    var selectionAcc = 0.0
    var mergeAcc = 0.0
    var bubbleAcc = 0.0
    repeat(times) {
        val result = experimentSort(agents)
        selectionAcc += result.first
        mergeAcc += result.second
        bubbleAcc += result.third
    }
    val selectionMean = selectionAcc / times
    val mergeMean = mergeAcc / times
    val bubbleMean = bubbleAcc / times

    return Triple(selectionMean, mergeMean, bubbleMean)
}

fun runSorts() {
    val selectionResults = mutableMapOf<Int, Double>()
    val mergeResults = mutableMapOf<Int, Double>()
    val bubbleResults = mutableMapOf<Int, Double>()
    for (i in 1..100) {
        val results = multipleExperimentsSort(i*100, 50)
        selectionResults[i*100] = results.first
        mergeResults[i*100] = results.second
        bubbleResults[i*100] = results.third
    }

    var selectionResult = ""
    selectionResults.forEach { (key, value) ->
        selectionResult += "($key, $value)\n"
    }
    File("selection.txt").writeText(selectionResult)

    var mergeResult = ""
    mergeResults.forEach { (key, value) ->
        mergeResult += "($key, $value)\n"
    }
    File("merge.txt").writeText(mergeResult)

    var bubbleResult = ""
    bubbleResults.forEach { (key, value) ->
        bubbleResult += "($key, $value)\n"
    }
    File("bubble.txt").writeText(bubbleResult)
}

fun experimentMax(agents: Int): Pair<Int, Int> {
    val l = (1..agents).map { Gaussian(meanDistribution.sample(), stdDevDistribution.sample().absoluteValue) }.toList()
    val simpleMax = l.max()
    val singleBrackets = l.singleBrackets()

    val simpleMaxError = l.map { it.mean }.wrongMax(simpleMax.mean)
    val singleBracketsError = l.map { it.mean }.wrongMax(singleBrackets.mean)

    return Pair(simpleMaxError, singleBracketsError)
}

fun multipleExperimentsMax(agents: Int, times: Int): Pair<Double, Double> {
    var simpleMaxAcc = 0.0
    var singleBracketsAcc = 0.0
    repeat(times) {
        val result = experimentMax(agents)
        simpleMaxAcc += result.first
        singleBracketsAcc += result.second
    }
    val selectionMean = simpleMaxAcc / times
    val mergeMean = singleBracketsAcc / times

    return Pair(selectionMean, mergeMean)
}

fun runMax() {
    val simpleMaxResults = mutableMapOf<Int, Double>()
    val singleBracketsResults = mutableMapOf<Int, Double>()
    for (i in 1..1000) {
        val results = multipleExperimentsMax(i*100, 500)
        simpleMaxResults[i*100] = results.first
        singleBracketsResults[i*100] = results.second
    }

    var simpleMaxResult = ""
    simpleMaxResults.forEach { (key, value) ->
        simpleMaxResult += "($key, $value)\n"
    }
    File("results/max/simpleMax.txt").writeText(simpleMaxResult)

    var singleBracketsResult = ""
    singleBracketsResults.forEach { (key, value) ->
        singleBracketsResult += "($key, $value)\n"
    }
    File("results/max/singleBrackets.txt").writeText(singleBracketsResult)
}

fun main() {
    runMax()
}