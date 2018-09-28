package cchcc.learn.amu.e06.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class E06DataRepository {
    private val dataSourceLiveData = MutableLiveData<E06ThreeTimesTableDataSource>()
    private val dataSourceFactory = E06ThreeTimesTableDataSourceFactory(dataSourceLiveData)

    fun listingThreeTimesTable() = LivePagedListBuilder(dataSourceFactory,
                PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setPageSize(10)
                        .build())
            .build()

    fun refresh() {
        dataSourceLiveData.value?.invalidate()
    }

}