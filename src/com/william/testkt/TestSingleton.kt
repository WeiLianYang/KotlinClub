package com.william.testkt

object TestSingleton {

    private val list: MutableList<Int> = ArrayList()

    fun addElement(x: Int) {
        list.add(x)
    }

    fun printList() {
        print(list.toString())
    }
}

fun main() {
    TestSingleton.addElement(1)
    TestSingleton.addElement(2)
    TestSingleton.addElement(3)

    TestSingleton.printList()

}