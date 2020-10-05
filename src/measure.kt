fun<T: Comparable<T>> List<T>.disorder(): Int = this.withIndex().sumBy { (i, first) ->
    this.withIndex().count { (j, second) ->
        ((i < j && first > second) || (i > j && first < second))
    }
}

fun<T: Comparable<T>> List<T>.wrongMax(max: T): Int = this.count { it > max }