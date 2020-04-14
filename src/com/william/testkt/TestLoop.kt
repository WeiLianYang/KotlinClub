package com.william.testkt

fun main() {

    val x = 5
    val y = 7
    if (x in 1..9) {
        println("x在区间内")
    }

    when (y) {
        8 -> println(y == 8)
        9 -> println(y == 9)
        else -> println("x in else block")
    }

    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }


    val array = arrayOf(4, 2, 1, 6, 9)
    for (xx in array) {
        print("$xx,")
    }
    println()
    for (i in array.indices) {
        print(array[i])
        print("-")
    }
    println()

    for ((index, value) in array.withIndex()) {
        println("#the element at $index is $value")
    }
    println()

    var list: ArrayList<String> = arrayListOf()
    list.add("bbb")
    list.add("ccc")

    for (l in list) {
        print("$l,")
    }
    println()

    // break用法，会
    loop@ for (i in 5..8) {
        for (j in 11..13) {
            if (i == 7) {
                break@loop
            }
            println("i->:$i, j->:$j")
        }
    }
    println()

    // continue用法
    loop@ for (i in 5..8) {
        for (j in 11..13) {
            if (i == 7) {
                continue@loop
            }
            println("i->:$i, j->:$j")
        }
    }

    println()

    foo1(array)

    println()

    foo2(array)

}

fun foo1(array: Array<Int>) {
    array.forEach {
        // 直接将整个方法return了
        if (it == 2) return
        print(it)
    }
}

fun foo2(array: Array<Int>) {
    array.forEach {
        // 只return forEach
        if (it == 2) return@forEach
        print(it)
    }
}