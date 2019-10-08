@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.*
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var a = 0.0
    for (element in v) {
        a += sqr(element)
    }
    return sqrt(a)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.isEmpty()) 0.0
    else {
        (list.sum() / list.size)
    }
}


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in 0 until a.size) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var px = 0
    var a = 1
    for (i in 0 until p.size) {
        px += p[i] * a
        a *= x
    }
    return px
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0
    for (i in 0 until list.size - 1) {
        sum = list[i] + list[i + 1]
        list[i + 1] = sum
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var num = n
    var del = 2
    while (!isPrime(num)) {
        if (num % del == 0) {
            result.add(del)
            num /= del
        } else del++
    }
    result.add(num)
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var mod = 0
    var a = n
    while (a > 0) {
        mod = a % base
        result.add(mod)
        a /= base
    }
    return result.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String =
    convert(n, base).joinToString(separator = "", transform = {
        if (it > 9) ('a' + it - 10).toString()
        else "$it"
    })

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val list = digits.reversed()
    var num10 = 0
    var pow = 1
    for (i in 0 until list.size) {
        num10 += list[i] * pow
        pow *= base
    }
    return num10
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Int>()
    for (i in str) {
        if (i.isDigit()) list.add(i - '0') else list.add(i - 'a' + 10)
    }
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val list = mutableListOf<String>()
    var num = n
    var a = 0
    a = num % 10
    num /= 10
    when (a) {
        1 -> list.add("I")
        2 -> list.add("II")
        3 -> list.add("III")
        4 -> list.add("IV")
        5 -> list.add("V")
        6 -> list.add("VI")
        7 -> list.add("VII")
        8 -> list.add("VIII")
        9 -> list.add("IX")
    }
    a = num % 10
    num /= 10
    when (a) {
        1 -> list.add("X")
        2 -> list.add("XX")
        3 -> list.add("XXX")
        4 -> list.add("XL")
        5 -> list.add("L")
        6 -> list.add("LX")
        7 -> list.add("LXX")
        8 -> list.add("LXXX")
        9 -> list.add("XC")
    }
    a = num % 10
    num /= 10
    when (a) {
        1 -> list.add("C")
        2 -> list.add("CC")
        3 -> list.add("CCC")
        4 -> list.add("CD")
        5 -> list.add("D")
        6 -> list.add("DC")
        7 -> list.add("DCC")
        8 -> list.add("DCCC")
        9 -> list.add("CM")
    }
    a = num % 10
    num /= 10
    when (a) {
        1 -> list.add("M")
        2 -> list.add("MM")
        3 -> list.add("MMM")
    }
    return list.reversed().joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    fun russianp(a: Int, list: MutableList<String>): String {
        val ab = a
        if ((ab !in 1..91 step 10) && (ab !in 0..20 step 10) && (ab !in 1..9)) {
            when (ab % 10) {
                2 -> list.add("двадцать")
                3 -> list.add("тридцать")
                4 -> list.add("сорок")
                5 -> list.add("пятьдесят")
                6 -> list.add("шестьдесят")
                7 -> list.add("семьдесят")
                8 -> list.add("восемьдесят")
                9 -> list.add("девяносто")
            }
            when (ab / 10) {
                1 -> list.add("одна")
                2 -> list.add("две")
                3 -> list.add("три")
                4 -> list.add("четыре")
                5 -> list.add("пять")
                6 -> list.add("шесть")
                7 -> list.add("семь")
                8 -> list.add("восемь")
                9 -> list.add("девять")
            }
        } else {
            when (ab) {
                10 -> list.add("один")
                2 -> list.add("две")
                3 -> list.add("три")
                4 -> list.add("четыре")
                5 -> list.add("пять")
                6 -> list.add("шесть")
                7 -> list.add("семь")
                8 -> list.add("восемь")
                9 -> list.add("девять")
                20 -> list.add("два")
                1 -> list.add("десять")
                11 -> list.add("одиннадцать")
                21 -> list.add("двенадцать")
                31 -> list.add("тринадцать")
                41 -> list.add("четырнадцать")
                51 -> list.add("пятнадцать")
                61 -> list.add("шестнадцать")
                71 -> list.add("семнадцать")
                81 -> list.add("восемнадцать")
                91 -> list.add("девятнадцать")
            }
        }
        return list.joinToString(separator = " ")
    }

    val list = mutableListOf<String>()
    var num = revert(n)
    var a = 0
    val count = digitNumber(n)
    if (count == 6) {
        a = num % 10
        num /= 10
        when (a) {
            1 -> list.add("сто")
            2 -> list.add("двести")
            3 -> list.add("триста")
            4 -> list.add("четыреста")
            5 -> list.add("пятьсот")
            6 -> list.add("шестьсот")
            7 -> list.add("семьсот")
            8 -> list.add("восемьсот")
            9 -> list.add("девятьсот")
        }
    }
    if (count > 3) {
        a = num % 100
        num /= 100
        russianp(a, list)
        when (a / 10) {
            1 -> list.add("тысяча")
            in 2..4 -> list.add("тысячи")
            in 5..9 -> list.add("тысяч")
        }
        if (a / 10 == 0) {
            when (a % 10) {
                0 -> list.add("тысяч")
                in 1..9 -> list.add("тысячи")
            }
        }
    }
    if (count > 2) {
        a = num % 10
        num /= 10
        when (a) {
            1 -> list.add("сто")
            2 -> list.add("двести")
            3 -> list.add("триста")
            4 -> list.add("четыреста")
            5 -> list.add("пятьсот")
            6 -> list.add("шестьсот")
            7 -> list.add("семьсот")
            8 -> list.add("восемьсот")
            9 -> list.add("девятьсот")
        }
    }
    if (count > 1) {
        a = num
        russianp(a, list)
    }
return list.joinToString(separator = " ")
}