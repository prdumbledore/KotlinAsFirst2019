@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1


/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val result = mutableMapOf<Int, List<String>>()
    for ((name, grade) in grades) {
        result[grade] = result.getOrPut(grade) { listOf() } + name
    }
    return result
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for ((key) in a) {
        if (a[key] != b[key]) return false
    }
    return true
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    for ((word) in b) {
        if (a[word] == b[word]) {
            a.remove(word)
        }
    }
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val filter = mutableSetOf<String>()
    for (i in a) {
        if (i in b) filter += i
    }
    return filter.toList()
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val res = mapA.toMutableMap()
    for ((key, value) in mapB) {
        if (key !in res) res[key] = value
        if (res[key] != mapB[key]) res[key] += ", ${mapB[key]}"
    }
    return res
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val averagePrice = mutableMapOf<String, Double>()
    val sumPrice = mutableMapOf<String, List<Double>>()
    for ((name, price) in stockPrices) {
        sumPrice[name] = sumPrice.getOrDefault(name, listOf()) + price
    }
    for ((name, price) in sumPrice) {
        averagePrice[name] = price.average()
    }
    return averagePrice
}

/**
 * Средняя//
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var productName: String?
    var min = Double.MAX_VALUE
    productName = null
    for ((name, pair) in stuff) {
        if ((pair.first == kind) && (pair.second <= min)) {
            min = pair.second
            productName = name
        }
    }
    return productName
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    val set = mutableSetOf<Char>()
    for (i in chars) {
        set.plusAssign(i.toLowerCase())
    }
    for (i in word.toLowerCase()) {
        if (i !in set) return false
    }
    return true
}
/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val extractor = mutableMapOf<String, Int>()
    for (i in list) {
        extractor[i] = extractor.getOrDefault(i, 0) + 1
    }
    return extractor.filter { it.value != 1 }
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    if (words.size == 1) return false
    if (words.isEmpty()) return false
    val map = mutableMapOf<String, Int>()
    for (word in words) {
        val letters = word.toCharArray().sorted().toString()
        map[letters] = map.getOrDefault(letters, 0) + 1
    }
    for ((key) in map) {
        if (map.getValue(key) > 1) return true
    }
    return false
}


/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    // Через цикл выдавало ошибку time out, решил сделать через рекурсию
    fun findHandshakes(
        handshake: Map<String, Set<String>>,
        i: String,
        set: MutableSet<String>,
        presentKey: String,
        checkedFriendsSet: MutableSet<String>
    ): MutableSet<String> { // функция для поиска всех возможных друзей для одного человека
        var n = i
        for (key in handshake.getValue(n)) {
            if ((key !in handshake.getValue(presentKey)) && (key != n) && (key != presentKey)) {
                set.add(key)
                checkedFriendsSet.add(n)
                if (key !in checkedFriendsSet) {
                    n = key
                    set.addAll(findHandshakes(handshake, key, set, presentKey, checkedFriendsSet))
                }
            }
        }
        return set
    }

    val handshake = mutableMapOf<String, MutableSet<String>>()  // итоговый map
    val set = mutableSetOf<String>() // вспомогательная переменная

    for ((key, value) in friends) {
        handshake[key] = handshake.getOrDefault(key, mutableSetOf())
        handshake[key]!! += value
    }

    for ((key) in friends) {     // тут создаю новые ключи для тех людей, у которых нет ключей
        for (i in friends.getValue(key)) {
            if (i.isNotEmpty()) handshake[i] = handshake.getOrDefault(i, mutableSetOf())
        }
    }

    for ((key, value) in handshake) {   //добавляю всех возможных друзей для key
        val allFriendsSet = mutableSetOf<String>() // полный список друзей для key
        val checkedFriendsSet = mutableSetOf<String>() // список уже проверенных друзей для key
        for (i in value) {
            if (i.isNotEmpty()) {
                allFriendsSet += findHandshakes(handshake, i, set, key, checkedFriendsSet)
            }
        }
        if (allFriendsSet.isNotEmpty()) handshake[key] = (value + allFriendsSet) as MutableSet<String>
        checkedFriendsSet.removeAll(checkedFriendsSet)
        allFriendsSet.removeAll(allFriendsSet)
        set.removeAll(set)
    }

    return handshake
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    val map = mutableMapOf<Int, Int>()
    for (i in 0 until list.size) {
        val first = number - list[i]
        val value = map[first]
        if (value != null && first in map) {
            return if (first > number / 2) i to value
            else value to i
        } else map[list[i]] = i
    }
    return Pair(-1, -1)
}


/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()        //Set<String>
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val counter = treasures.size
    val bagPack = mutableMapOf<Set<String>, Pair<Int, Int>>()
    for ((key, value) in treasures) bagPack[setOf(key)] = value

    val table = Array(counter + 1) { Array<Set<String>>(capacity + 1) { setOf() } } // нулевая таблица
    var priceInTopCell = 0
    var priceInTopAndMinWeightCell = 0
    val setPriceInTopCell = mutableSetOf<String>()
    val setPriceInTopAndMinWeightCell = mutableSetOf<String>()

    for (i in 1 until counter + 1) {    // прогоняю таблицу, заполняя её максимальными стоимостями для определенного веса
        val weight = treasures.getValue(treasures.keys.elementAt(i - 1)).first
        val price = treasures.getValue(treasures.keys.elementAt(i - 1)).second
        for (j in 1 until capacity + 1) {
            if (weight <= j) {
                if (table[i - 1][j].isNotEmpty()) {
                    for ((item) in bagPack) {
                        for (itemName in item) {
                            if (itemName !in setPriceInTopCell) {
                                setPriceInTopCell += itemName
                                if (itemName in table[i - 1][j]) priceInTopCell += bagPack.getValue(item).second
                            }
                        }
                    }
                }
                if (table[i - 1][j - weight].isNotEmpty()) {
                    for ((item) in bagPack) {
                        for (itemName in item) {
                            if (itemName !in setPriceInTopAndMinWeightCell) {
                                setPriceInTopAndMinWeightCell += itemName
                                if (itemName in table[i - 1][j - weight])
                                    priceInTopAndMinWeightCell += bagPack.getValue(item).second
                            }
                        }
                    }
                }

                if (priceInTopCell <= priceInTopAndMinWeightCell + price) {
                    val set = mutableSetOf<String>()
                    set += (table[i - 1][j - weight] + bagPack.keys.elementAt(i - 1))
                    table[i][j] = set
                } else table[i][j] = table[i - 1][j]
            } else table[i][j] = table[i - 1][j]
            priceInTopCell = 0
            priceInTopAndMinWeightCell = 0
            setPriceInTopCell.removeAll(setPriceInTopCell)
            setPriceInTopAndMinWeightCell.removeAll(setPriceInTopAndMinWeightCell)
        }
    }
    // На выходе получается таблица с ключами, где в самой правой нижней ячейке будет множество ключей,
    // сумма цен которых является наибольшей возможной.

    return table[counter][capacity]
}




