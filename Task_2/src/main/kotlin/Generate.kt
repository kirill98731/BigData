import java.io.File
import java.math.BigInteger
import java.util.*

fun generate(file_name: String, size: Int) {
    val arr = arrayListOf<BigInteger>()
    for (i in 0 until size) {
        arr.add(BigInteger(64, Random()))
    }
    File(file_name).writeText(arr.joinToString("\n"))
}

fun main(args: Array<String>) {
    generate("big_arr_48.txt", 2000)
}