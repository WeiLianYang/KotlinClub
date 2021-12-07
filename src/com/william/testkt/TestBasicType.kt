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
 * @date 2021/12/7 14:47
 * @description  Kotlin 基本类型
 */
fun main(args: Array<String>) {

    // val 声明 只读变量
    // var 声明 读写变量

    // 1. 数值型
    // 整数型
    val a: Byte = 1 // 8位
    val b: Short = 2 // 16位
    val c: Int = 3 // 32
    val c2 = 3 // 默认整型 Int
    val d: Long = 4 // 64
    val d2 = 4L // 带后缀 L 标识 Long 长整型

    // 浮点型
    val e: Float = 3.14f
    val e2 = 3.14f
    val f: Double = 3.14

    // 2. 布尔型
    val state: Boolean = true
    val state2 = false

    // 3. 字符型
    // 直接指定单个字符作为字符值
    val aChar: Char = 'a'

    // 使用转义字符作为字符值
    val covChar: Char = '\r'

    // 使用Unicode编码值来指定字符值
    val ch: Char = '\u5475'
    println(ch) // 呵

    // 4. 字符串
    val str1: String = "str1"
    val str2 = "str2"

    // 字符串模板
    val str3 = "$str1 length: ${str1.length}"
    println(str3) // str1 length: 4

    // 会保留字符串中的缩进，常用于排版打印
    val str4 = """
        I am a programmer,
        175cm
    """.trimMargin()

    println(str4)

    // 原始字符串, "|"作为边界符，"|"之前的缩进都会去掉
    val str5 = """
        |I am a programmer,
        |175cm
    """.trimMargin()

    println(str5)

    // 自定义"&"作为边界符，"&"之前的缩进都会去掉
    val str6 = """
        &I am a programmer,
        &175cm
    """.trimMargin("&")

    println(str6) // 结果同 "|" 作为边界符

    // 5. 可空类型
    var aa: Byte? = null
    aa = 1

    var bb: Short? = null
    bb = 2

    var cc: Int? = null
    cc = 3

    var dd: Long? = null
    dd = 4

    var ee: Float? = null
    ee = 5f

    var ff: Double? = null
    ff = 6.0

    var gg: Boolean? = null
    gg = false

    // 6. 类型相互转换
    aa.toInt()
    aa.toShort()
    aa.toLong()
    aa.toFloat()
    aa.toDouble()

    // 安全调用，使用 ?.
    cc = null
    cc?.toLong()
    cc?.toString()

}