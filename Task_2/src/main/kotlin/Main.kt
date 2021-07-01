

fun main(args: Array<String>) {
    var file_name = "big_arr_48.txt"
    generate(file_name, 2000)
    simple_solution(file_name)
    completable_future(file_name)
    flowable(file_name)
    akka(file_name)
}