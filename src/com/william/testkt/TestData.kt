package com.william.testkt

data class Users(val name: String, val age: Int)

fun main(args: Array<String>) {

    val jack = Users(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    println(jack)
    println(olderJack)

    // 组件函数允许数据类在解构声明中使用
    val jane = Users("Jane", 35)
    val (name, age) = jane
    println("$name, $age years of age") // prints "Jane, 35 years of age"


}