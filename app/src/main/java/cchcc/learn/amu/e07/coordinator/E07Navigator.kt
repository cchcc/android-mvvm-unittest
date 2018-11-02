package cchcc.learn.amu.e07.coordinator

interface E07Navigator {
    // the reason why using generic T is to avoid clarifying a type `Bundle`
    // which is the platform dependency.  maybe not the best way..
    fun <T> navigateWord(args: Map<String, T>)
    fun navigateBackToWordList()
}
