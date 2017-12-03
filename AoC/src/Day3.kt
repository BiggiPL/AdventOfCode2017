import java.awt.Point
import kotlin.math.absoluteValue
import kotlin.math.max

fun main(args: Array<String>) {

    Day3_1(1)
    Day3_1(12)
    Day3_1(23)
    Day3_1(1024)
    Day3_1(312051)


    Day3_2(10)
    Day3_2(312051)

}

class Day3_1(private val searched: Long) {

    init {
        println(getQuarter())
        println()
    }

    fun getQuarter(actualValue: Long = 1, actualSideSize: Long = 0): Long {
        val checkedSideSize = actualSideSize + 1

        var startValue = actualValue
        var newValue = actualValue + checkedSideSize

        if (newValue > searched) {
            println("side ${if (checkedSideSize % 2 != 0L) "bottom" else "top"}")
            println("$searched in side with length $checkedSideSize and ended by $newValue")

            val first = checkedSideSize / 2
            val second = (searched - (startValue + first)).absoluteValue
            println("$first and $second")
            return first + second
        }

        startValue = newValue
        newValue += checkedSideSize

        if (newValue > searched) {
            println("side ${if (checkedSideSize % 2 == 0L) "right" else "left"}")
            println("$searched in side with length $checkedSideSize and ended by $newValue")

            val first = checkedSideSize / 2 + 1
            val second = (searched - (startValue + first - 1)).absoluteValue
            println("$first and $second")
            return first + second
        }

        return getQuarter(newValue, checkedSideSize)
    }

}


class Day3_2(private val searched: Long) {

    private val list = Array(1000, { _ -> Array(1000, { _ -> 0L }) })

    init {
        list[500][500] = 1

        val lastPosition = fillUntil()

        val radius = max((lastPosition.x - 500).absoluteValue, (lastPosition.y - 500).absoluteValue)


        for(i in 500-radius..500+radius){
            for(j in 500-radius..500+radius) {
                print("\t${list[i][j]}")
            }
            println()
        }
    }


    fun fillUntil(length: Int = 0, lastPosition: Point = Point(500, 500), lastDir: Point = Point(0, 1)): Point {
        val newLength = length + 1
        var newDir = nextDir(lastDir)
        var newPosition = lastPosition


        for (i in 1..newLength) {
            newPosition = Point(newPosition.x + newDir.x, newPosition.y + newDir.y)
            val value = getValue(newPosition)

            list[newPosition.x][newPosition.y] = value

            if (value > searched) {
                println(value)
                return newPosition
            }
        }

        newDir = nextDir(newDir)

        for (i in 1..newLength) {
            newPosition = Point(newPosition.x + newDir.x, newPosition.y + newDir.y)
            val value = getValue(newPosition)

            list[newPosition.x][newPosition.y] = value

            if (value > searched) {
                println(value)
                return newPosition
            }
        }

        return fillUntil(newLength, newPosition, newDir)
    }

    fun getValue(position: Point): Long {
        var sum = 0L
        sum += list[position.x - 1][position.y]
        sum += list[position.x - 1][position.y + 1]
        sum += list[position.x][position.y + 1]
        sum += list[position.x + 1][position.y + 1]
        sum += list[position.x + 1][position.y]
        sum += list[position.x + 1][position.y - 1]
        sum += list[position.x][position.y - 1]
        sum += list[position.x - 1][position.y - 1]

        return sum
    }

    fun nextDir(dir: Point): Point {
        when {
            dir.x == 1 && dir.y == 0 -> return Point(0, -1)
            dir.x == 0 && dir.y == -1 -> return Point(-1, 0)
            dir.x == -1 && dir.y == 0 -> return Point(0, 1)
        }

        return Point(1, 0)
    }

}