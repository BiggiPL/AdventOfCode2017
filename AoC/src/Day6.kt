fun main(args: Array<String>) {

    Day6_1(example_6)
    Day6_1(contest_6)

    Day6_2(example_6)
    Day6_2(contest_6)

}

class Day6_1(input: String) {

    private val history = mutableListOf<String>()
    private val value = input.split(" ").map { s -> s.toInt() }.toMutableList()
    private var counter = 0

    init {
        findFirstRepetition()
    }

    private fun findFirstRepetition() {

        while (!history.contains(getStringValue())) {
            redistributeHighest()
        }

        println("$counter steps")

    }

    private fun redistributeHighest() {
        history.add(getStringValue())

        val maximum = value.max()
        var position = value.indexOfFirst { i -> i == maximum }
        var points = value[position]
        value[position] = 0
        position = incrementIndex(position)

        while (points > 0) {
            value[position]++
            points--
            position = incrementIndex(position)
        }
        counter++
//        println(getStringValue())
    }

    private fun incrementIndex(currentValue: Int): Int {
        return (currentValue + 1) % value.size
    }

    private fun getStringValue(): String {
        return value.joinToString(" ")
    }

}

class Day6_2(input: String) {

    private val history = mutableListOf<String>()
    private val value = input.split(" ").map { s -> s.toInt() }.toMutableList()
    private var counter = 0

    init {
        findFirstRepetition()
    }

    private fun findFirstRepetition() {

        while (!history.contains(getStringValue())) {
            redistributeHighest()
        }

        println(history.size - history.indexOf(getStringValue()))

        println("$counter steps")

    }

    private fun redistributeHighest() {
        history.add(getStringValue())

        val maximum = value.max()
        var position = value.indexOfFirst { i -> i == maximum }
        var points = value[position]
        value[position] = 0
        position = incrementIndex(position)

        while (points > 0) {
            value[position]++
            points--
            position = incrementIndex(position)
        }
        counter++
//        println(getStringValue())
    }

    private fun incrementIndex(currentValue: Int): Int {
        return (currentValue + 1) % value.size
    }

    private fun getStringValue(): String {
        return value.joinToString(" ")
    }

}

val example_6 = "0 2 7 0"

val contest_6 = "5 1 10 0 1 7 13 14 3 12 8 10 7 12 0 6"