/*
 * Copyright WeiLianYang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.william.testkt

import java.util.concurrent.Callable


/**
 * author：William
 * date：2021/12/8 21:40
 * description：泛型
 */
fun main() {

    // 1. 定义泛型类
    open class GenericDemo<T>(open var field: T? = null)

    // 继承泛型类
    class SubGeneric(param: Float) : GenericDemo<Float>() {
        override var field: Float? = param
    }

    val demo1 = GenericDemo<String>("demo1")
    val demo2 = GenericDemo<Int>(2)
    val demo3 = GenericDemo(true)

    val demo4 = SubGeneric(3.14f)

    println("${demo1.field}, ${demo2.field}, ${demo3.field}, ${demo4.field}") // demo1, 2, true, 3.14

    // 2. 定义类型变
    // kotlin 使用 out 定义型变。即向外提供的类型，不能向其写入，同 java 的通配符上限
    // name 只能用 val 声明，否则会有 setter 方法向其写入
    class Fruit<out T>(private val name: T) {
        fun getFruitName(): T {
            return name
        }
    }

    val apple = Fruit("apple")

    // 此时 Fruit<String> 可以被赋值给 Fruit<Any>
    val fruit: Fruit<Any> = apple
    println(fruit.getFruitName())

    // 3. 定义类逆变
    // kotlin 使用 in 定义逆变。即向里写入的类型，不能向外提供，同 java 通配符下限
    class Reverse<in T> {
        fun setValue(value: T) {
            println("value: $value")
        }
    }

    var reverse1 = Reverse<Int>()
    val reverse2 = Reverse<Number>()
    reverse2.setValue(3.14f) // value: 3.14

    // 可以将 Reverse<Number> 赋值给 Reverse<Int>
    reverse1 = reverse2

    // 4. 定义调用处型变
    var arrNumber: ArrayList<out Number> = arrayListOf(1, 0.2, 3f)
    val arrayInt: ArrayList<Int> = arrayListOf(1, 2, 3)

    // arrNumber 可以接受元素为 Int 类型集合
    arrNumber = arrayInt
    println(arrNumber) // [1, 2, 3]

    // 5. 定义调用处逆变
    var arrInt: ArrayList<in Int> = arrayListOf(1, 2, 3)
    val arrNum: ArrayList<Number> = arrayListOf(1, 0.2, 3f)

    // arrayInt 可以接受元素为 Number 类型集合
    arrInt = arrNum
    println(arrInt) // [1, 0.2, 3.0]

    // 6. 星号投影
    // val list : ArrayList = arrayListOf(1, 2, 3) // 编译错误
    // 使用 * 号，等价于 out Any? 可接受任意类型的元素
    val list1: ArrayList<*> = arrayListOf(1, 0.2, "3")
    val list2: ArrayList<out Any?> = arrayListOf(1, 0.2, "3")
    println("list1: $list1, list2: $list2") // list1: [1, 0.2, 3], list2: [1, 0.2, 3]

    // 7. 泛型函数
    fun <T> printSelf(param: T) {
        println(param)
    }
    printSelf(2)

    // 8. 使用 reified 具型化参数
    printSelfName<Int>() // class simple name: Int
    printSelfName<String>() // class simple name: String

    // 9. 定义类型形参的上限，类定义，或方法定义

    // 定义类泛型上限
    class Container<T : Number>(var param: T)

    val c1 = Container<Int>(1)
    val c3 = Container<Long>(3L)
    val c4 = Container<Float>(3.14f)

    // 定义方法泛型上限
    fun <T : Number> sum(vararg param: T) {
        var sum = 0.0
        param.forEach {
            sum += it.toDouble()
        }
        println("求和: $sum")
    }
    sum(1, 2f, 3.0) // 求和: 6.0

    // 类泛型指定多个上限
    class Test<T>() where T : Callable<T>, T : Runnable {}

    // 方法泛型指定多个上限
    fun <T> func(param: T) where T : Callable<T>, T : Runnable {}

}

inline fun <reified T> printSelfName() {
    val name = T::class.simpleName
    println("class simple name: $name")
}
