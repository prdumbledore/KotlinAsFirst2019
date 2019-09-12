@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson5.task1.buildGrades
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int):
        String = when {
    (age % 100 == 11) -> "$age лет"
    (age % 100 == 12) -> "$age лет"
    (age % 100 == 13) -> "$age лет"
    (age % 100 == 14) -> "$age лет"
    (age % 10 == 1) -> "$age год"
    (age % 10 == 2) -> "$age года"
    (age % 10 == 3) -> "$age года"
    (age % 10 == 4) -> "$age года"
    else -> "$age лет"
}
/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double): Double =
    if ((t1 * v1 + t2 * v2 + t3 * v3) / 2 > (t1 * v1 + t2 * v2)) {
     t1 + t2 + ((t1 * v1 + t2 * v2 + t3 * v3) / 2 - (t1 * v1 + t2 * v2)) / v3
    }
    else if ((t1 * v1 + t2 * v2 + t3 * v3) / 2 > (t1 * v1)) {
     t1 + ((t1 * v1 + t2 * v2 + t3 * v3) / 2 - (t1 * v1)) / v2
    }
        else {
        (t1 * v1 + t2 * v2 + t3 * v3) / 2 / v1
    }

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int): Int =
    when {
        ((kingX == rookX1) and (kingX == rookX2)) -> 3
        ((kingX == rookX1) and (kingY == rookY2)) -> 3
        ((kingY == rookY1) and (kingX == rookX2)) -> 3
        ((kingY == rookY1) and (kingY == rookY2)) -> 3
        (kingY == rookY2) or (kingX == rookX2) -> 2
        (kingY == rookY1) or (kingX == rookX1) -> 1
    else -> 0
    }
/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int =
    when {
        ((kingX == rookX) or (kingY == rookY)) and (abs(kingX - bishopX) == abs(kingY - bishopY)) -> 3
        abs(kingX - bishopX) == abs(kingY - bishopY) -> 2
        (kingX == rookX) or (kingY == rookY) -> 1
        else -> 0
    }

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int =
    when {
        (a + b > c) and (c + b > a) and (a + c > b) and ((sqr(a) == sqr(b) + sqr(c)) or (sqr(b) == sqr(a) + sqr(c)) or (sqr(c) == sqr(b) + sqr(a))) -> 1
        (a + b > c) and (c + b > a) and (a + c > b) and ((sqr(a) > sqr(b) + sqr(c)) or (sqr(b) > sqr(a) + sqr(c)) or (sqr(c) > sqr(b) + sqr(a))) -> 2
        (a + b > c) and (c + b > a) and (a + c > b) and ((sqr(a) < sqr(b) + sqr(c)) or (sqr(b) < sqr(a) + sqr(c)) or (sqr(c) < sqr(b) + sqr(a))) -> 0
        else -> -1

    }

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int =
if ((a <= c) and (c <= b) and (b <= d)) {
    b-c
}
    else if  ((a <= c) and (d <= b)) {
    d-c
}
    else if ((c <= a) and (b <= d)) {
    b-a
}
    else if ((c <= a) and (a <= d) and (d <= b)) {
    d-a
}
else -1