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
 * Kotlin 可以对一个类的属性和方法进行扩展，且不需要继承或使用 Decorator 模式。
 * 扩展是一种静态行为，对被扩展的类代码本身不会造成任何影响。
 */
class User(var name: String)

// 扩展函数
fun User.print() {
    println("your name is $name")
}

// 扩展函数 swap,调换不同位置的值
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    // this关键字指代接收者对象(receiver object)(也就是调用扩展函数时, 在点号之前指定的对象实例)。
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}


// 扩展函数是静态解析的，并不是接收者类型的虚拟成员，在调用扩展函数时，具体被调用的的是哪一个函数，
// 由调用函数的的对象表达式来决定的，而不是动态的类型决定的
// 若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数或子类中的成员函数
open class CCC {
    open fun foo() {
        println("ccc-成员函数")
    }
}

class DDD : CCC() {
    override fun foo() {
        println("ddd-成员函数")
    }
}

fun CCC.foo() = "c-扩展函数"   // 扩展函数 foo

fun DDD.foo() = "d-扩展函数"   // 扩展函数 foo

fun printFoo(c: CCC) {
    println(c.foo())  // 类型是 C 类
}

// 在扩展函数内， 可以通过 this 来判断接收者是否为 NULL,这样，即使接收者为 NULL,也可以调用扩展函数。
fun Any?.toString(): String {
    if (this == null) return "null"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}

fun Int?.toString(): String {
    if (this == null) return "--0--"
    // 空检测之后，“this”会自动转换为非空类型，所以下面的 toString()
    // 解析为 Any 类的成员函数
    return toString()
}

// 伴生对象的扩展函数
class MyClass {
    companion object {}  // 将被称为 "Companion"
}

fun MyClass.Companion.foo() {
    println("伴随对象的扩展函数")
}

val MyClass.Companion.no: Int
    get() = 10

// 在一个类内部你可以为另一个类声明扩展。
// 在这个扩展中，有个多个隐含的接受者，
// 其中扩展方法定义所在类的实例称为分发接受者，而扩展方法的目标类型的实例称为扩展接受者。
// 在 C 类内，创建了 D 类的扩展。
// 此时，C 被成为分发接受者，而 D 为扩展接受者。
// 从下例中，可以清楚的看到，在扩展函数中，可以调用分发接收者的成员函数。
class D_ {
    fun bar() {
        println("D bar")
    }
}

class C_ {
    fun baz() {
        println("C baz")
    }

    fun bar() {
        println("C bar")
    }

    fun D_.foo() {
        bar()   // 调用 D.bar
        baz()   // 调用 C.baz
    }

    fun D_.foo2() {
        bar()         // 调用 D.bar()，扩展接收者优先
        this@C_.bar()  // 调用 C.bar()
    }

    fun caller(d: D_) {
        d.foo()   // 调用扩展函数
        d.foo2()
    }
}

fun main(args: Array<String>) {
    val user = User("William")
    user.print()

    val l = mutableListOf(1, 2, 3)
    // 位置 0 和 2 的值做了互换
    l.swap(0, 2) // 'swap()' 函数内的 'this' 将指向 'l' 的值
    println(l.toString())

    printFoo(DDD())

    val t: Int? = null
    println(t.toString())

    println("no:${MyClass.no}")
    MyClass.foo()

    val c: C_ = C_()
    val d: D_ = D_()
    c.caller(d)

}