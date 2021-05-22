package subtask2

class BillCounter {

    // TODO: Complete the following function
    // The output could be "Bon Appetit" or the string with number(e.g "10")
    fun calculateFairlySplit(bill: IntArray, k: Int, b: Int): String {
        //throw NotImplementedError("Not implemented")
        var generalSum = 0
        for ((i, value) in bill.withIndex()) {
            if (i == k) continue
            generalSum += value
        }
        var cost = if (b == generalSum / 2) "bon appetit" else (b - generalSum / 2).toString()
        return cost
    }
}
