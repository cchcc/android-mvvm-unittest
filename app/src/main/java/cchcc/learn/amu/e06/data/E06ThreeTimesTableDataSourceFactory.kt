package cchcc.learn.amu.e06.data

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import java.util.concurrent.ExecutorService

class E06ThreeTimesTableDataSourceFactory(private val dataSourceLiveData: MutableLiveData<E06ThreeTimesTableDataSource>) : DataSource.Factory<Int, E06Number>() {
    override fun create(): DataSource<Int, E06Number> = E06ThreeTimesTableDataSource().apply {
        dataSourceLiveData.postValue(this)
    }
}