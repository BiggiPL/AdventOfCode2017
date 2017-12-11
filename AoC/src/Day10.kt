import sun.font.NativeFont

fun main(args: Array<String>) {
    Day10_1(example10_1, 4)
    Day10_1(contest10, 255)

    Day10_2(example10_2)
    Day10_2(example10_3)
    Day10_2(example10_4)
    Day10_2(example10_5)
    Day10_2(contest10)
}

class Day10_1(input: String, hashListSize: Int) {

    private val hashList = (0..hashListSize).toMutableList()

    init {
        var skipSize = 0
        var position = 0

        input.split(",").forEach { c ->
            val value = c.toInt()
            reverse(position, value)

//            println("$value ($position, $skipSize) - $hashList")

            position = (position + value + skipSize++) % hashList.size
        }

        println("$input - ${hashList[0] * hashList[1]}")
    }

    private fun reverse(position: Int, value: Int) {
        val max = value - 1
//        println("REVERSE $position , $value , $max")
        for (i in 0..(max / 2)) {
            val lowValue = (position + i) % hashList.size
            val highValue = (position + max - i) % hashList.size
//            println("$i - $lowValue - $highValue")
            val temp = hashList[lowValue]
            hashList[lowValue] = hashList[highValue]
            hashList[highValue] = temp
        }
    }
}

class Day10_2(input: String) {

    private val hashList = (0..255).toMutableList()

    init {
        var skipSize = 0
        var position = 0

        val byteInput: MutableList<Int> = input.map { c -> c.toByte().toInt() }.toMutableList()
        byteInput.addAll(listOf(17, 31, 73, 47, 23))

        for (j in 1..64) {
            byteInput.forEach { i ->
                reverse(position, i)
                position = (position + i + skipSize++) % hashList.size
            }
        }

        val result = mutableListOf<Int>()
        for (i in 0..15) {
            var XOR: Int? = null
            for (j in i * 16..(i * 16 + 15)) {
                if (XOR == null) {
                    XOR = hashList[j]
                } else {
                    XOR = XOR.xor(hashList[j])
                }
            }

            if (XOR != null) {
                result.add(XOR)
            }
        }

//        println("$hashList\n$result")

        val hexString = result.map { i: Int -> String.format("%02x", i) }.joinToString("", "", "")

        println("$input - $hexString")
    }

    private fun reverse(position: Int, value: Int) {
        val max = value - 1
        for (i in 0..(max / 2)) {
            val lowValue = (position + i) % hashList.size
            val highValue = (position + max - i) % hashList.size
            val temp = hashList[lowValue]
            hashList[lowValue] = hashList[highValue]
            hashList[highValue] = temp
        }
    }
}

val example10_1 = """3,4,1,5"""

val example10_2 = """1,2,3"""
val example10_3 = ""
val example10_4 = """AoC 2017"""
val example10_5 = """1,2,4"""

val contest10 = """106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36"""