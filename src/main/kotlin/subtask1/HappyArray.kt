package subtask1

class HappyArray {

    // TODO: Complete the following function
    fun convertToHappy(sadArray: IntArray): IntArray {
        //throw NotImplementedError("Not implemented")
        var happyArray: IntArray;
        var tempSadArray: MutableList<Int> = sadArray.toMutableList();
        do {
            var tempArrayOfRemovingIndexes = mutableListOf<Int>();
            var i = 1;
            while (i < tempSadArray.size - 1) {
                if (tempSadArray[i - 1] + tempSadArray[i + 1] < tempSadArray[i]) {
                    tempArrayOfRemovingIndexes.add(i);
                    i++;
                }
                i++;
            }
            for (index in tempArrayOfRemovingIndexes.size - 1 downTo 0) {
                tempSadArray.removeAt(tempArrayOfRemovingIndexes[index]);
            }
        } while (tempArrayOfRemovingIndexes.isNotEmpty());
        happyArray = tempSadArray.toIntArray();
        return happyArray;
    }
}
