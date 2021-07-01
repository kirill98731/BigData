import io.reactivex.rxjava3.core.Flowable
import java.io.File
import kotlin.system.measureTimeMillis


fun flowable(file_name: String) {
    var sum = 0L
    val time = measureTimeMillis {
        val numbers = File(file_name).readLines().map { it.toBigInteger() }
        Flowable.fromIterable(numbers)
            .parallel()
            .map { x -> Count_Factors(x) }
            .sequential()
            .forEach({x -> sum += x })
    }
    println("---------\nС помощью RxJava\nСуммарное количество простых множителей: $sum")
    println("Время работы: ${time/1000}")
}

fun main(args: Array<String>) {
    flowable("big_arr_48.txt")
}