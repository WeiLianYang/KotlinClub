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


// 指定文件生成的 Java 类或方法的名称。
@file:JvmName("CallKtDemo")

package com.william.testkt

var field: Int = 100

fun method() {
    println("call CallKtDemo method")
}

class TestCallKotlin {
    companion object {
        fun method1() {
            println("call method1")
        }

        @JvmStatic
        fun method2() {
            println("call method2")
        }
    }
}

object KotlinObject {

    fun method1() {
        println("call method1")
    }

    @JvmStatic
    fun method2() {
        println("call method2")
    }

    // 参数为 lambda 表达式，入参为 Int，返回值为 Unit，在定义时，也可以不写等号后面的表达式
    @JvmStatic
    fun method3(param: (Int) -> Unit = {}) {
        param.invoke(101)
    }

    // 参数为 lambda 表达式，入参为 Boolean，返回值为 String，在定义时，也可以不写等号后面的表达式
    @JvmStatic
    fun method4(param: (Boolean) -> String = { "" }) {
        param.invoke(true)
    }

    // 参数为 lambda 表达式，无入参，返回值为 Unit
    @JvmStatic
    fun method5(param: () -> Unit) {
        param.invoke()
    }

    // 参数为 lambda 表达式，入参为 Int, String，返回值为 Boolean
    @JvmStatic
    fun method6(param: (Int, String) -> Boolean) {
        param.invoke(100, "string value")
    }
}
