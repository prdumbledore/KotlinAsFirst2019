@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point) : this(linkedSetOf(a, b, c))

    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        val distance = center.distance(other.center) - (radius + other.radius)
        return if (distance > 0) distance else 0.0
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = center.distance(p) - radius <= 1e-6
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
        other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
        begin.hashCode() + end.hashCode()
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    var diameter = 0.0
    var pair1 = Point(0.0, 0.0)
    var pair2 = Point(0.0, 0.0)
    require(points.size > 1)
    for (p1 in 0 until points.size - 1) {
        for (p2 in p1 + 1 until points.size) {
            val distance = sqrt(sqr(points[p1].x - points[p2].x) + sqr(points[p1].y - points[p2].y))
            if (distance > diameter) {
                diameter = distance
                pair1 = Point(points[p1].x, points[p1].y)
                pair2 = Point(points[p2].x, points[p2].y)
            }
        }
    }
    return Segment(pair1, pair2)
}


/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle =
    Circle(
        Point((diameter.begin.x + diameter.end.x) / 2, (diameter.begin.y + diameter.end.y) / 2),
        sqrt(sqr(diameter.begin.x - diameter.end.x) + sqr(diameter.begin.y - diameter.end.y)) / 2
    )


/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double) {
    init {
        require(angle >= 0 && angle < PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double) : this(point.y * cos(angle) - point.x * sin(angle), angle)

    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point {
        val x =
            (other.b * cos(angle) - b * cos(other.angle)) / (sin(angle) * cos(other.angle) - cos(angle) * sin(other.angle))
        val y = if (angle == Math.PI / 2) (x * sin(other.angle) + other.b) / cos(other.angle)
        else (x * sin(angle) + b) / cos(angle)
        return Point(x, y)
    }

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${cos(angle)} * y = ${sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line = lineByPoints(s.begin, s.end)

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line {
    if (a.x == b.x) return Line(a, Math.PI / 2)
    if (a.y == b.y) return Line(a, 0.0)
    val angle = atan2(b.y - a.y, b.x - a.x)
    return if (angle >= 0) Line(a, angle)
    else Line(a, PI + angle)
}

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val line = lineByPoints(a, b)
    val middlePoint = Point((a.x + b.x) / 2, (a.y + b.y) / 2)
    return Line(middlePoint, (line.angle + PI / 2) % PI)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    var diameter = Double.MAX_VALUE
    var minDistance = Pair(circles[0], circles[0])
    require(circles.size > 1)
    for (circle1 in 0 until circles.size - 1) {
        for (circle2 in circle1 + 1 until circles.size) {
            val distance = circles[circle1].distance(circles[circle2])
            if (distance <= diameter) {
                diameter = distance
                minDistance = Pair(circles[circle1], circles[circle2])
            }
            if (distance == 0.0) return Pair(circles[circle1], circles[circle2])
        }
    }
    return minDistance
}

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val p = bisectorByPoints(a, b).crossPoint(bisectorByPoints(a, c))
    return Circle(p, p.distance(a))
}

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun minContainingCircle(vararg points: Point): Circle {
    require(points.isNotEmpty())
    if (points.size == 1) return Circle(points[0], 0.0)
    if (points.size == 2) return circleByDiameter(Segment(points[0], points[1]))

    val diameter = diameter(*points)
    var result = circleByDiameter(diameter(*points))
    val pointList = points.filter { it != diameter.end && it != diameter.begin }
    var radiusMin = if (points.all { result.contains(it) }) result.radius
    else Double.MAX_VALUE

    for (p in pointList) {
        val triangleCircle = circleByThreePoints(diameter.begin, diameter.end, p)
        if (radiusMin > triangleCircle.radius && points.all {triangleCircle.contains(it)}) {
            radiusMin = triangleCircle.radius
            result = triangleCircle
        }
    }
    return result
}

