import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.UntypedAbstractActor
import java.io.File
import java.math.BigInteger


data class Count(val count: Long)
data class Number(val num: BigInteger)
data class Start(val file_name: String)

class Count_Factor : UntypedAbstractActor() {
    override fun onReceive(message: Any?) {
        when(message) {
            is Number -> {
                sender.tell(Count(Count_Factors(message.num)), self)
            }
        }
    }

}

class MainActor : UntypedAbstractActor() {
    var sum = 0L
    var workersCount = 0
    var finish = 0
    var time_start = System.currentTimeMillis()
    override fun onReceive(message: Any?) {
        when(message) {
            is Start -> {
                val numbers = File(message.file_name).readLines().map { it.toBigInteger() }
                workersCount = numbers.size
                val workers = Array(workersCount) { context.actorOf(Props.create(Count_Factor::class.java)) }
                for (i in 0 until workersCount) {
                    workers[i].tell(Number(numbers[i]), self)
                }
            }
            is Count -> {
                sum+=message.count
                finish+=1
                if (finish == workersCount){
                    var time_end = System.currentTimeMillis()
                    println("---------\nС помощью Akka\nСуммарное количество простых множителей: $sum")
                    println("Время работы: ${(time_end-time_start)/1000}")
                    context.system.terminate()
                }
            }
            else -> {
                context.system.terminate()
            }
        }
    }

}

fun akka(file_name: String){
    val system = ActorSystem.create("MySystem")
    val mainActor = system.actorOf(Props.create(MainActor::class.java), "main")
    mainActor.tell(Start(file_name), null)
}

fun main(args: Array<String>) {
    akka("big_arr_48.txt")
}