package subtask3

class StringParser {

    // TODO: Complete the following function
    fun getResult(inputString: String): Array<String> {
        //throw NotImplementedError("Not implemented")
        val arrayOfBrackets = arrayOf(
            Brackets('[', ']'),
            Brackets('(', ')'),
            Brackets('<', '>')
        )
        val task = TaskSubstringInBrackets(inputString, arrayOfBrackets)
        task.findAllSubstrings()
        val result = task.getAllSubstrings()
        return result
    }

    class TaskSubstringInBrackets(
        private val mainString: String,
        private val brackets: Array<Brackets>
    ) {
        private val substringsList: MutableList<String> = mutableListOf()
        private val bracketsPosList: MutableList<BracketsPos> = mutableListOf()
        private val ignorePosOfClosingBracketsList: MutableList<Int> = mutableListOf()

        fun getAllSubstrings(): Array<String> {
            return substringsList.toTypedArray()
        }

        fun findAllSubstrings() {
            findAllBracketsPos()
            for (bracketsPos in bracketsPosList) {
                val substring = mainString.substring(bracketsPos.posBegin + 1, bracketsPos.posEnd)
                substringsList.add(substring)
            }
        }

        private fun findAllBracketsPos() {
            for ((posSymbol, symbol) in mainString.withIndex()) {
                val bracketsType = getTypeOfBrackets(symbol)
                if (bracketsType == -1) continue
                val bracketOpeningPos = posSymbol
                var counterOfSameOpeningBrackets = 0;
                var counterPos = posSymbol + 1
                while (true) {
                    if (mainString[counterPos] == brackets[bracketsType].closing) {
                        if (checkIgnoringPosOfClosingBrackets(counterPos)) {
                            counterPos++
                            continue
                        } else {
                            if (counterOfSameOpeningBrackets != 0) {
                                counterOfSameOpeningBrackets--
                                counterPos++
                                continue
                            } else {
                                break
                            }
                        }
                    }
                    if (mainString[counterPos] == symbol) counterOfSameOpeningBrackets++
                    counterPos++
                }
                val bracketClosingPos = counterPos
                val bracketsPos = BracketsPos(bracketOpeningPos, bracketClosingPos)
                bracketsPosList.add(bracketsPos)
            }
        }

        private fun getTypeOfBrackets(bracketSymbol: Char): Int {
            var index = -1
            for ((i, bracket) in brackets.withIndex()) {
                if (bracket.opening == bracketSymbol) {
                    index = i
                    break
                }
            }
            return index
        }

        private fun checkIgnoringPosOfClosingBrackets(posOfClosingBrackets: Int): Boolean {
            var isIgnore = false
            for (pos in ignorePosOfClosingBracketsList) {
                if (pos == posOfClosingBrackets) {
                    isIgnore = true
                    break
                }
            }
            return isIgnore
        }
    }

    class Brackets(val opening: Char, val closing: Char)
    class BracketsPos(val posBegin: Int, val posEnd: Int)
}
