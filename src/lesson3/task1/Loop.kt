@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var result = 1
    var number = n
    while (number / 10 > 0) {
        result += 1
        number /= 10
    }
    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var fib2 = 1
    var fib3 = 1
    var num = n
    while (num > 2) {
        fib3 = fib2 + fib1
        fib1 = fib2
        fib2 = fib3
        num -= 1
    }
    return fib3
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var a = m
    var b = n
    val p = a * b
    while ((a != 0) && (b != 0)) {
        if (a > b)
            a %= b
        else
            b %= a
    }
    return (p / (a + b))
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var a = n
    for (i in 2..(sqrt(n.toDouble()).toInt())) {
        if (n % i == 0) {
            a = i
            break
        }
    }
    return a
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if ((m == 2) && (n == 2)) return true
    if ((m % 2 == 0) && (n % 2 == 0)) return false
    if ((n % m == 0) || (m % n == 0))
    for (i in 3..n step 2) {
        if ((m % i == 0) && (n % i == 0)) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n) {
        if ((sqrt(i.toDouble()) % 1.0 == 0.0))
            return true
    }
    return false
}
/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var step = 0
    var a = x
    if (a <= 1) 0
    while (a != 1) {
        if (a % 2 == 0) {
            step += 1
            a /= 2
        }
        else {
            step += 1
            a = a * 3 + 1
        }
    }
    return step
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var a = x % (2.0 * PI)
    var sin = 0.0
    var i = 1
    var counter = 1
    while (abs(a) >= eps) {
        if (counter % 2 != 0) {
            a = x.pow(i) / factorial(i)
            i += 2
            counter++
        }
        else {
            a = - (x.pow(i) / factorial(i))
            i += 2
            counter++
        }
        sin += a
    }
    return sin
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var a = x % (2.0 * PI)
    var cos = 1.0
    var i = 2
    var counter = 2
    while (abs(a) >= eps) {
        if (counter % 2 == 0) {
            a = - (x.pow(i) / factorial(i))
            i += 2
            counter++
        }
        else {
            a = x.pow(i) / factorial(i)
            i += 2
            counter++
        }
        cos += a
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var a = n
    var res = 0
    while (a > 0) {
        res *= 10
        res += (a % 10)
        a /= 10
    }
    return res
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun hasDifferentDigits(n: Int): Boolean {
    var a = n
    var x1 = 0
    var x2 = 0
    while (a > 9) {
        x1 = a % 10
        x2 = (a / 10) % 10
        a /= 10
        if (x1 == x2) continue
        else return true
    }
    return false
}
/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var a = n
    var num = 0
    var i = 1
    var digit = 0
    var res = 0
    while (a > 0) {
        num = i * i
        i++
        digit = digitNumber(num)
        a -= digit
    }
    if (a == 0) {
        res = num % 10
    }
    if (a < 0) {
        a += digit
        num = revert(num)
        while (a != 0) {
            a -= 1
            res = num % 10
            num /= 10
        }
    }
    return res
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var a = n
    var num = 0
    var i = 1
    var digit = 0
    var res = 0
    while (a > 0) {
        num = fib(i)
        i++
        digit = digitNumber(num)
        a -= digit
    }
    if (a == 0) {
        res = num % 10
    }
    if (a < 0) {
        a += digit
        num = revert(num)
        while (a != 0) {
            a -= 1
            res = num % 10
            num /= 10
        }
    }
    return res
}