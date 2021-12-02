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
 * 类型别名
 */
// 为map指定别名
typealias map = HashMap<String, ArrayList<String>>

// 为类指定别名
class A {
    // 内部类InnerA
    inner class InnerA
}

class B {
    // 内部类InnerB
    inner class InnerB
}

typealias a_in = A.InnerA
typealias b_in = B.InnerB

// Kotlin允许为Lambda表达式的类型指定别名, 结果为Boolean型
typealias Filter<T> = (T) -> Boolean

fun main(args: Array<String>) {
    // 直接用别名声明变量
    var testMap: map

    // 直接用a_in和b_in声明变量，调用对象
    // 这里的InnerA和InnerB都是内部类
    var a: a_in = A().a_in()
    var b: b_in = B().b_in()

    val filter: Filter<String> = { it.length > 4 }
    // 为filter()方法传入参数p，只保留长度大于4的字符串
    println(arrayOf("java", "kotlin", "javascript", "oc").filter(filter))// [kotlin, javascript]
}