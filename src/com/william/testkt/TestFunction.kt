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

import java.lang.Integer.min
import kotlin.math.max

/**
 * author：William
 * date：2021/12/3 20:09
 * description：Kotlin 函数 和 Lambda 表达式
 */
fun main() {

    // 1. 调用函数
    function1()
    function2(1, "2")
    function3(3)
    function4(1, 2)

    // 2. 调用函数时指定参数的值
    function5(1, 2)
    function5(param1 = 1, 2)
    function5(1, param2 = 2)
    function5(param1 = 1, param2 = 2)
    function5(param2 = 2, param1 = 1)

    // 3. 调用函数时使用参数默认值，对减少函数重载非常有用
    // 建议将有默认值的参数放在形参列表的最后
    function6() // param1: -1, param2: -2
    function6(3) // param1: 3, param2: -2
    function6(3, 4) // param1: 3, param2: 4
    function6(param2 = 4) // param1: -1, param2: 4

    // 4. 数量可变参数
    function7(0, "str1", "str2", "str3")

    // 使用 * 将数组传入可变参数
    val arr = arrayOf("str1", "str2", "str3")
    function7(0, *arr)

    // 5. 局部函数
    function8("max", 1, 2) // 2
    function8("min", 1, 2) // 1
    function8("multiply", 2, 3) // 6

    // 6. 高阶函数
    // 使用函数的引用来调用函数，kotlin 使用 :: 引用函数
    val funcRefer = ::function3
    // 使用函数引用加括号来调用函数
    funcRefer(1) // 1

    // func1 的函数类型为 (Int, String) -> String
    fun func1(p0: Int, p1: String): String {
        return p1
    }

    // func2 的函数类型为 (Int) -> Int
    fun func2(p0: Int): Int {
        println("func2 param: $p0")
        return p0
    }

    // func3 的函数类型为 () -> Unit
    fun func3() {
    }

    // 将函数类型作为参数使用
    fun func4(param: Int, fn: (Int) -> Int) {
        fn(param)
    }
    func4(100, ::func2) // func2 param: 100

    // 将函数类型作为返回值使用。这里只做模拟，直接返回函数 func2 的引用
    fun func5(): (Int) -> Int {
        return ::func2
    }
    // 获取返回的函数
    val funcResult = func5()
    funcResult(200) // func2 param: 200
    funcResult.invoke(200) // 同上 func2 param: 200
    func5()(200) // 同上 func2 param: 200

    // 匿名函数
    val anonymousFunc = fun(p0: Int, p1: Int): Int {
        return p0 + p1
    }
    println(anonymousFunc(1, 2)) // 3

    // 7. lambda 表达式，相当于简化的匿名函数
    // 定义 lambda 表达式，参数为 Int，返回值为 String 类型
    val lambda: (Int) -> String = { it ->
        // it 默认指代参数
        it.toString()
    }
    println("lambda result: ${lambda(300)}") // lambda result: 300

    // 也可以自行指定参数名为 param
    val lambda2: (Int) -> String = { param ->
        param.toString()
    }

    // 当不使用入参时，也可以指定参数名为 _
    val lambda3: (Int) -> String = { _ ->
        "nothing"
    }

    // 定义函数，返回类型为 函数 (Int, Int) -> Int
    fun getFunc(type: String): (Int, Int) -> Int {
        return when (type) {
            "max" -> { p0: Int, p1: Int ->
                max(p0, p1)
            }
            "min" -> { p0: Int, p1: Int ->
                min(p0, p1)
            }
            else -> { p0: Int, p1: Int ->
                p0 * p1
            }
        }
    }
    // 获取求最小值函数
    val funcLambda = getFunc("min")
    println(funcLambda(2, 3)) // 2


    // 9. 内联函数
    // 调用内联函数，当最后一个参数为 lambda 时，可以将表达式写在括号外面。同 Swift 的后置闭包
    val list = compute(arrayListOf(1, 2, 3)) { it * it }
    println(list) // [1, 4, 9]

    funcCross { it * it } // 9
}


/**
 * 定义函数。无参，无返回值。
 * 也可以使用 Unit 表明没有返回值，同 Java 的 void
 */
fun function1() {
    println("no params func")
}

/**
 * 定义函数，返回2个参数拼接的结果字符串
 *
 * @param param1 int
 * @param param2 String
 * @return String
 */
fun function2(param1: Int, param2: String): String {
    val result = param1.toString() + param2
    println(result)
    return result
}

/**
 * 定义函数，返回结果为可空字符串
 *
 * @param param Int
 * @return String? nullable
 */
fun function3(param: Int): String? {
    if (param < 0) {
        return null
    }
    println(param)
    return param.toString()
}

/**
 * 单表达式函数，返回2个参数的和
 *
 * @param param1 int
 * @param param2 int
 * @return int param1 + param2
 */
fun function4(param1: Int, param2: Int) = param1 + param2


fun function5(param1: Int, param2: Int) {
    println("param1: $param1, param2: $param2")
}

/**
 * 定义函数，指定参数默认值
 *
 * @param param1 int
 * @param param2 int
 */
fun function6(param1: Int = -1, param2: Int = -2) {
    println("param1: $param1, param2: $param2")
}


/**
 * 定义函数，参数数量可变。
 * 同 Swift func(param: Int...) { }
 *
 * @param param1 int
 * @param param2 String 数量可以是多个
 */
fun function7(param1: Int, vararg param2: String) {
    println("param1: $param1")
    param2.forEach {
        println("param2 item: $it")
    }
}

/**
 * 定义函数，内嵌3个局部函数
 */
fun function8(type: String, param1: Int, param2: Int): Int {
    println("param1: $param1, param2: $param2")

    fun funcInner1(p0: Int, p1: Int): Int {
        return max(p0, p1)
    }

    fun funcInner2(p0: Int, p1: Int): Int {
        return min(p0, p1)
    }

    fun funcInner3(p0: Int, p1: Int): Int {
        return p0 * p1
    }

    val result = when (type) {
        "max" -> funcInner1(param1, param2)
        "min" -> funcInner2(param1, param2)
        else -> funcInner3(param1, param2)
    }
    println("function8: $result")
    return result
}


/**
 * 定义内联函数
 * 当形参中含有比较 函数类型 或 lambda 表达式时，为了减少函数执行过程中压栈和出栈产生的时间和空间开销
 * 可以使用内联 inline 简化函数，相当于将 fn 函数复制到执行处执行
 * 注意：当 fn 中含有大量代码时，不适合使用内联，因为当大量被调用时， 复制 fn 的代码会造成更大的性能开销
 *
 * @param list
 * @param fn 将被内联化
 *
 * @return list
 */
inline fun compute(list: List<Int>, fn: (Int) -> Int): ArrayList<Int> {
    val newList = arrayListOf<Int>()
    list.forEach {
        val newValue = fn(it) // val newValue = it * it , 直接将表达式复制到了这里
        newList.add(newValue)
    }
    return newList
}

/**
 * 选择性内联
 * 定义内联函数，使用 noinline 修饰，阻止 fn2 被内联
 * @param fn1 将被内联
 * @param fn2 不会被内联
 */
inline fun func(fn1: (Int) -> Int, noinline fn2: (Int) -> Int) {
    println(fn1(1))
    println(fn2(1))
}


/**
 * 当 函数 或 lambda表达式 是在其他的上下文中被调用时，使用 crossinline
 * 如果不加，会提示：不能在这里内联'fn':它可能包含非本地返回。
 * 意思是 fn 中含有 return 代码，会直接结束函数。
 *
 * @param fn 不是直接在函数的上下文中调用
 */
inline fun funcCross(crossinline fn: (Int) -> Int) {
    val thread = Thread(Runnable {
        println(fn(3))
    })
    thread.start()
}

