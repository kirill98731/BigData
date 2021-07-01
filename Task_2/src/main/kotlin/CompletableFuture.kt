import java.io.File
import java.math.BigInteger
import java.util.concurrent.CompletableFuture
import kotlin.system.measureTimeMillis

fun completable_future(file_name: String) : Unit {
    var sum = 0L
    val lines: List<String> = File(file_name).readLines()

    val time = measureTimeMillis {
        for (i in (0..(lines.size - 1) step 4)) {
            val f1 = CompletableFuture.supplyAsync { Count_Factors(BigInteger(lines[i])) }
            val f2 = CompletableFuture.supplyAsync { Count_Factors(BigInteger(lines[i + 1])) }
            val f3 = CompletableFuture.supplyAsync { Count_Factors(BigInteger(lines[i + 2])) }
            val f4 = CompletableFuture.supplyAsync { Count_Factors(BigInteger(lines[i + 3])) }
            val f5 = CompletableFuture
                .allOf(f1,f2,f3,f4)
                .thenApply {f1.get() + f2.get() + f3.get() + f4.get()}
            sum += f5.get()
        }

    }
    println("---------\nМногопоточно, с использованием CompletableFuture\nСуммарное количество простых множителей: $sum")
    println("Время работы: ${time/1000}")
}

fun main(args: Array<String>) {
    completable_future("big_arr_48.txt")
}