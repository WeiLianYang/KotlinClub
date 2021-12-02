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

/**
 * @author WilliamYang
 * @date 2021/11/29 10:14
 * @description kotlin 运算符
 */
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

    // 2. kotlin 双目算术运算符
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

    // 3. kotlin 赋值运算符
    a += b // 7, 同 a = a + b
    a -= b // 3, 同 a = a - b
    a *= b // 12, 同 a = a * b
    a /= b // 3, 同 a = a / b

    // 4. kotlin 比较运算符
    println(a > b) // false，同 a.compareTo(b) > 0
    println(a < b) // true，同 a.compareTo(b) < 0
    println(a >= b) // false，同 a.compareTo(b) >= 0
    println(a <= b) // true，同 a.compareTo(b) <= 0

    println("-----------------------------")

    // 5. kotlin 位运算
    // kotlin 按位与：操作数的两位同时为1，才为1
    println(a.and(b)) // 0
    // kotlin 按位或：操作数的两位只要一个为1，即为1
    println(a.or(b)) // 7
    // kotlin 按位非：操作数的每个位全取反
    println(a.inv()) // -4
    // kotlin 按位异或：操作数的两位相同时返回0，不同时返回1
    println(a.xor(b)) // 7
    // kotlin 左移 左乘
    println(a.shl(2)) // 12
    // kotlin 右移 右除
    println(a.shr(1)) // 1
    // kotlin 无符号右移
    println(a.ushr(1)) // 1

    println("-----------------------------")

    // 6. kotlin 区间运算符
    // kotlin 定义闭区间
    var range = 1..3 // [1,3] 范围是 1-3，含边界
    for (ele in range) {
        print(ele * 2) // 2 4 6
    }

    println()

    val array = intArrayOf(1, 2, 3, 4)
    // kotlin 定义开区间
    range = 0 until array.size // [0,4) 范围是1-4，不含4，即[0,3]
    for (ele in range) {
        print(array[ele]) // 1 2 3 4
    }

    println()

    // kotlin 定义反向区间，从3到0，闭区间
    val rangeInt: IntProgression = 3 downTo 0
    for (ele in rangeInt) {
        print(ele) // 3 2 1 0
    }
    println()
    // kotlin 定义反向区间步长
    for (ele in rangeInt step 2) {
        print(ele) // 3 1
    }

    println("-----------------------------")

    // 7. kotlin 重载运算符
    // kotlin 重载单目运算符
    var cla = OperatorClass(1, 1)
    println(-cla) // 调用的是 unaryMinus() OperatorClass(a=-1, b=-1)
    println(!cla) // 调用的是 not()        OperatorClass(a=-1, b=-1)

    println(++cla) // 调用的是 inc() OperatorClass(a=2, b=2)
    println(--cla) // 调用的是 dec() OperatorClass(a=1, b=1)

    // kotlin 重载双目运算符
    val cla1 = OperatorClass(1, 2)
    val cla2 = OperatorClass(3, 4)

    println("-----------------------------")

    var operResult: OperatorClass

    operResult = cla1 + cla2
    println(operResult) // OperatorClass(a=4, b=6)

    operResult = cla1 * cla2
    println(operResult) // OperatorClass(a=3, b=8)
}

/**
 * @author WilliamYang
 * @date 2021/11/29 16:57
 * @description 重载运算符数据类
 */
data class OperatorClass(val a: Int, val b: Int) {

    // 单目运算 -
    operator fun unaryMinus(): OperatorClass {
        return OperatorClass(-a, -b)
    }

    // 单目运算 非 !
    operator fun not(): OperatorClass {
        return OperatorClass(-a, -b)
    }

    // 单目运算 自增
    operator fun inc(): OperatorClass {
        return OperatorClass(a + 1, b + 1)
    }

    // 单目运算 自减
    operator fun dec(): OperatorClass {
        return OperatorClass(a - 1, b - 1)
    }

    // 双目运算 相加 +
    operator fun plus(other: OperatorClass): OperatorClass {
        return OperatorClass(a + other.a, b + other.b)
    }

    // 双目运算 相乘 *
    operator fun times(other: OperatorClass): OperatorClass {
        return OperatorClass(a * other.a, b * other.b)
    }
}
