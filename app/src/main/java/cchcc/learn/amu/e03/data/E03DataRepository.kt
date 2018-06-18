package cchcc.learn.amu.e03.data

interface E03DataRepository<T> {
    val list: List<T>

    fun add(item: T)
    fun removeAt(idx: Int)
}