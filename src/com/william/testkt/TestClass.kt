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

class ClassA constructor(var fName: String = "") {

    constructor(fName: String, age: Int) : this(fName) {
        println("constructor:name---->$fName")
        println("constructor:age---->$age")
        this.age = age
        this.name = "default name"
    }

    init {
        println(fName)
        fName = "FFFF"
    }

    var firstName = fName
        get() = field.toLowerCase()

    var name: String = "name"
        get() = field.toUpperCase()

    var age: Int = 0
        get() = field
        set(value) {
            field = if (value < 100) {
                value
            } else {
                -1
            }
        }

    var height: Float = 154.3f
        private set

    var allByDefault: Int? = null // 错误: 需要一个初始化语句, 默认实现了 getter 和 setter 方法
    var initialized = 1    // 类型为 Int, 默认实现了 getter 和 setter
    val simple: Int? = null       // 类型为 Int ，默认实现 getter ，但必须在构造函数中初始化
    val inferredType = 1   // 类型为 Int 类型,默认实现 getter

    fun foo() {
        println("I am a function")
    }
}

class ClassB {
    private lateinit var obj: ClassA

    //    @SetUp
    fun setup() {
        obj = ClassA("aaa")
    }

    //    @Test
    fun test() {
        obj.foo()
    }
}

open class Base {
    open fun functionA() {

    }
}

abstract class subClass : Base() {
    abstract override fun functionA()
}

// 内部类
class Outer {                  // 外部类
    private val bar: Int = 1

    class Nested {             // 嵌套类
        fun foo(): Int = 2

        fun foo2(): String {
            return "2"
        }

        fun foo3() = 1 + 3
    }
}


class Outer2 {
    private val bar: Int = 1
    var v = "成员属性"

    fun outerFun(): String {
        return "类方法"
    }

    /**嵌套内部类**/
    inner class Inner {
        fun foo() = bar  // 访问外部类成员
        fun innerTest() {
            var o = this@Outer2 // 获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.outerFun())
        }
    }
}

/**
 * 定义接口
 */
interface TestInterfaces {
    fun test()
}

class Test {
    var v = "成员属性"

    fun setInterfaces(test: TestInterfaces) {
        test.test()
    }
}

fun main() {
    val defaultInstance = ClassA()
    val instance1 = ClassA("YANg")
    val instance2 = ClassA("YangC", 28)
    println("instance2: ${instance2.firstName}, ${instance2.name}, ${instance2.age}")

    instance1.name = "William"
    instance1.age = 9
    println("instance1: ${instance1.firstName}, ${instance1.name}, ${instance1.age}")
    instance1.foo()

    val instanceB = ClassB()
    instanceB.setup()
    instanceB.test()

    val demo = Outer.Nested().foo() // 调用格式：外部类.嵌套类.嵌套类方法/属性
    println(demo)    // 2
    println(Outer.Nested().foo3())

    println("------------------")

    val demo2 = Outer2().Inner().foo()
    println(demo2) //   1
    Outer2().Inner().innerTest()// 内部类可以引用外部类的成员，例如：成员属性

    val test = Test()
    // 采用对象表达式来创建接口对象，即匿名内部类的实例
    test.setInterfaces(object : TestInterfaces {
        override fun test() {
            println("对象表达式创建匿名内部类的实例")
        }
    })


}


