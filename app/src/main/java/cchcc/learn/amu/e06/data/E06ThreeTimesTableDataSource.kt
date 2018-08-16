package cchcc.learn.amu.e06.data

import android.arch.paging.PageKeyedDataSource
import java.util.concurrent.ExecutorService

class E06ThreeTimesTableDataSource() : PageKeyedDataSource<Int, E06Number>() {

    private fun generateThreeTimesTablePositive(pivot: Int, size: Int): List<Int> = List(size) { pivot + ((it + 1) * 3) }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, E06Number>) {
        val list = generateThreeTimesTablePositive(0, params.requestedLoadSize).map { E06Number(it, 0.0f) }
        callback.onResult(list, list.first().num, list.last().num)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, E06Number>) {
        val list = generateThreeTimesTablePositive(params.key, params.requestedLoadSize).map { E06Number(it, 0.0f) }
        callback.onResult(list, list.last().num)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, E06Number>) {
    }
}