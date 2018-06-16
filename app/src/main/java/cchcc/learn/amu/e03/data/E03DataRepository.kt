package cchcc.learn.amu.e03.data

interface E03DataRepository<T> {
    val list: List<T>

    fun add(item: T): Boolean
    fun remove(idx: Int): Boolean
}