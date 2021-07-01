import java.io.File
import java.math.BigInteger
import kotlin.system.measureTimeMillis


fun simple_solution(fileName: String){
    var sum = 0L
    val lines: List<String> = File(fileName).readLines()
    val time = measureTimeMillis {
        lines.forEach {
            line -> sum += Count_Factors(BigInteger(line))
        }
    }
    println("---------\nПростой последовательный алгоритм\nСуммарное количество простых множителей: $sum")
    println("Время работы: ${time/1000}")
}

fun main(args: Array<String>) {
    simple_solution("big_arr_48.txt")
}
