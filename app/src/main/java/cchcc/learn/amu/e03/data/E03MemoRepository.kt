package cchcc.learn.amu.e03.data

class E03MemoRepository : E03DataRepository<E03Memo> {

    private val memos by lazy {
        "The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically."
                .split(' ')
                .map(::E03Memo)
                .toMutableList()
    }

    override val list: List<E03Memo> by lazy { memos }

    override fun add(item: E03Memo) {
        memos.add(0, item)
    }

    override fun removeAt(idx: Int) {
        memos.removeAt(idx)
    }

}