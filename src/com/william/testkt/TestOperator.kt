package com.william.testkt

/**
 * @author WilliamYang
 * @date 2021/11/29 10:14
 * @description kotlin 运算符
 */
class TestOperator {


}

fun main(args: Array<String>) {

    // 1. kotlin 单目运算
    var field = 1

    // kotlin 自增运算符
    field++
    ++field
    field.inc() // 同自增运算

    // kotlin 自减运算符
    field--
    --field
    field.dec() // 同自减运算

    println(field) // 1

    // 2. kotlin 双目运算
    var a = 3
    val b = 4
    var result = 0

    result = a + b // 7，同a.plus(b)
    result = a - b // -1，同a.minus(b)
    result = a * b // 12，同a.times(b)
    result = a / b // 0，同a.div(b)
    result = a % b // 3，同a.rem(b)

    println(result)

    // kotlin in !in 判断包含关系
    val source = "input text"
    val target = "text"
    println(target in source) // true
    println(target !in source) // false

    println("-----------------------------")

    // kotlin 赋值运算符
    a += b // 7, 同 a = a + b
    a -= b // 3, 同 a = a - b
    a *= b // 12, 同 a = a * b
    a /= b // 3, 同 a = a / b

    // kotlin 比较运算符
    println(a > b) // false，同 a.compareTo(b) > 0
    println(a < b) // true，同 a.compareTo(b) < 0
    println(a >= b) // false，同 a.compareTo(b) >= 0
    println(a <= b) // true，同 a.compareTo(b) <= 0

    println("-----------------------------")

    // kotlin 位运算
    // kotlin 按位与
    println(a.and(b)) // 0
    println(a.or(b))


}