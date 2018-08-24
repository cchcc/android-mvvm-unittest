package cchcc.learn.amu.e07.coordinator

class E07WordCoordinator(var navigator: E07Navigator? = null) {
    fun backToWordListScreen() = navigator!!.navigateBackToWordList()
}