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

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


fun main() {
    val result = writeFile("this is a simple message")
    println(result) // 3

    val text = readFile()
    println(text) // true

    compute("1")
    compute("aaa")

    throwExFun("")

    throwCustomExFun(null)

}

// 定义顶级常量
const val fileName = "src/com/william/testkt/exception_demo.txt"

/**
 * 写入文件
 */
fun writeFile(src: String): Int {
    var fos: FileOutputStream? = null
    try {
        val file = File(fileName)
        fos = FileOutputStream(file)
        fos.write(src.toByteArray())
        return 1 // 会被 finally 块中的代码覆盖
    } catch (e: Exception) {
        e.printStackTrace()
        return 2 // 会被 finally 块中的代码覆盖
    } finally {
        fos?.close()
        return 3
    }
}

/**
 * 读取文件
 */
fun readFile(): String {
    var fis: FileInputStream? = null
    try {
        val file = File(fileName)
        val size = file.length().toInt()
        fis = FileInputStream(file)
        val sb = StringBuilder()
        val buffer = ByteArray(size)
        fis.read(buffer)
        sb.append(String(buffer))
        return sb.toString()
    } catch (e: Exception) {
        e.printStackTrace() // 打印堆栈信息
        return "${e.message}"
    } finally {
        println("finally")
        fis?.close()
    }
}

/**
 * 先处理小异常，再处理大异常
 */
fun compute(obj: String?) {
    try {
        Integer.parseInt(obj)
    } catch (e: RuntimeException) {
        println("RuntimeException: ${e.message}")
    } catch (e: Exception) {
        println("Exception: ${e.message}")
    }
}

/**
 * 使用 throw 抛出异常
 */
fun throwExFun(param: String?) {
    if (param == null) {
        throw NullPointerException()
    }
}

/**
 * 自定义异常
 */
class CustomException : Exception {
    // 无参构造
    constructor() {}

    // 带参构造
    constructor(msg: String) : super(msg) {}
}

/**
 * 使用 throw 抛出自定义异常
 */
fun throwCustomExFun(param: String?) {
    if (param == null) {
        throw CustomException("param is null")
    }
}

