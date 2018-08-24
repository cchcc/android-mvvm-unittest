package cchcc.learn.amu.e07.coordinator

class E07WordListCoordinator(var navigator: E07Navigator? = null) {
    fun goWordScreen() = navigator!!.navigateWord()
}