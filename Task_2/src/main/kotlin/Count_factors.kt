import java.math.BigInteger


fun Count_Factors(n: BigInteger): Long {
    var count = 0L
    var n = n

    if (n < BigInteger.TWO) return 0
    if (n.isProbablePrime(20)) {
        return 1
    }

    var f = BigInteger.TWO
    while (true) {
        if (n % f == BigInteger.ZERO) {
            count += 1
            n /= f
            if (n == BigInteger.ONE) return count
            if (n.isProbablePrime(20)) f = n
        } else if (f >= BigInteger.valueOf(3)) f += BigInteger.TWO
        else f = BigInteger.valueOf(3)
    }
}

fun main(args: Array<String>) {
    println(Count_Factors(BigInteger.valueOf(2345532345)))
}
