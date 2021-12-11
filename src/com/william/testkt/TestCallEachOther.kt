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


fun main() {

    // 一、Kotlin 调用 Java
    // 1. java 中的方法或变量 是kotlin 的关键字时，使用反引号 `` 对关键字进行转义
    val demo = JavaDemo()

    // 调用 java 的 is 属性，is 是 kotlin 的关键字，使用反引号转义
    demo.`is` = "call java field"
    println(demo.`is`) // call java field

    // 调用 java 的 in 方法，in 是 kotlin 的关键字，使用反引号转义
    demo.`in`() // call java method in

    // 2. 可将数组传给 java 的可变数量参数
    val arrayInt = intArrayOf(1, 2, 3)
    demo.multiParams(*arrayInt) // [1, 2, 3]

    // 3. 获取 kotlin 对象的 java 类
    demo::class.java
    demo.javaClass

}

class KotlinDemo {

    var field: String = "kotlin field"

    fun method() {
        println("call kotlin method")
    }

    /**
     * @JvmOverloads 注解会生成多个重载的方法
     *
     * fun overloadMethod(p0: Int = 0)
     * fun overloadMethod(p0: Int = 0, p1: Int = 1)
     * fun overloadMethod(p0: Int = 0, p1: Int = 1, p2: String = "")
     *
     */
    @JvmOverloads
    fun overloadMethod(p0: Int = 0, p1: Int = 1, p2: String = "") {
    }

    // kotlin 没有 checked 异常，使用 @Throws 抛出异常
    @Throws(IllegalArgumentException::class)
    fun method2() {
        println("call kotlin method2")
    }
}

val topLevelField: Int
    get() {
        println("call kotlin top level field")
        return 1
    }

fun topLevelFunc() {
    println("call kotlin top level function")
}