package cchcc.learn.amu.e06.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class E06ThreeTimesTableDataSourceFactory(private val dataSourceLiveData: MutableLiveData<E06ThreeTimesTableDataSource>) : DataSource.Factory<Int, E06Number>() {
    override fun create(): DataSource<Int, E06Number> = E06ThreeTimesTableDataSource().apply {
        dataSourceLiveData.postValue(this)
    }
}