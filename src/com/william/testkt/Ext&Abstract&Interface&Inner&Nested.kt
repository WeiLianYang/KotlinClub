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
 * author：William
 * date：2021/12/5 18:36
 * description：面向对象
 */
fun main() {

    // 一、kotlin 扩展
    // 扩展的本质：并没有真正修改所扩展的类，只是定义了一个函数，当调用扩展方法时，执行静态解析，由编译时类型决定，属于函数调用
    // 调用扩展方法和扩展属性
    val extIns = ExtClass()
    extIns.field3 = true
    extIns.printField() // field1: 100, field2: value str
    val objStr: String? = null
    val newStr = objStr.getNoNull("no null")
    println(newStr) // no null

    // 二、kotlin 抽象类
    val abstractImpl = AbstractImpl()
    println(abstractImpl.field)
    abstractImpl.method()

    // 三、kotlin 接口
    val interfaceImpl = InterfaceImpl()
    println(interfaceImpl.field)
    interfaceImpl.methodA()
    interfaceImpl.methodB()

    // 四、kotlin 内部类和嵌套类
    // 通过外部类的实例，初始化内部类
    val innerClass = OuterClassA().InnerClass()
    innerClass.method()

    // 嵌套类A，可直接使用类名调用，而不是通过外部类的实例调用
    val nestedClassA = OuterClassB.NestedClassA()
    nestedClassA.method()
    // 嵌套类B
    val nestedClassB = OuterClassB.NestedClassB()
    nestedClassB.method()
}

// 定义类
class ExtClass {
    var field1: Int = 100
    var field2: String = "value str"
}

/**
 * kotlin 定义扩展方法
 */
fun ExtClass.printField() {
    println("field1: $field1, field2: $field2")
}

/**
 * kotlin 定义扩展属性
 */
var ExtClass.field3: Boolean
    get() = field1 > 80
    set(value) = println(value)

/**
 * 为 String 定义扩展方法，如果调用方为 null, 则返回默认值，且一定不会为 null
 */
fun String?.getNoNull(defValue: String = ""): String {
    return this ?: defValue
}

/**
 * kotlin 定义抽象类
 */
abstract class AbstractClass {

    // 定义抽象属性，被子类实现
    abstract var field: Int

    // 定义抽象方法，被子类实现
    abstract fun method()
}

/**
 * kotlin 实现抽象类
 */
class AbstractImpl : AbstractClass() {

    // 实现抽象类属性
    override var field: Int
        get() = 1024
        set(value) {
            println(value)
        }

    // 实现抽象类方法
    override fun method() {
        println("invoke in AbstractImpl")
    }
}

/**
 * kotlin 定义接口
 */
interface InterfaceA {

    // 定义属性，被实现类实现
    var field: Int

    // 定义方法，被实现类实现
    fun methodA()
}

/**
 * kotlin 定义接口
 */
interface InterfaceB {

    // 定义方法，被实现类实现
    fun methodB()
}

/**
 * kotlin 实现接口，多实现
 */
class InterfaceImpl : InterfaceA, InterfaceB {

    // 实现接口属性
    override var field: Int
        get() = 128
        set(value) {
            println(value)
        }

    // 实现接口 A 的方法
    override fun methodA() {
        println("invoke methodA in ContractImpl")
    }

    // 实现接口 B 的方法
    override fun methodB() {
        println("invoke methodB in ContractImpl")
    }
}


/**
 * kotlin 定义内部类
 * 同 java 的非静态内部类
 */
class OuterClassA {
    val field: Int = 100

    fun outerMethod() {
        println("invoke outer method")
    }

    // 内部类使用 inner 修饰
    inner class InnerClass {
        // 在内部类中可以访问外部类的属性和方法
        fun method() {
            println(field)
            outerMethod()
        }
    }
}

/**
 * kotlin 定义嵌套类
 * 同 java 的静态内部类
 */
class OuterClassB {
    val field: Int = 0

    fun outerMethod() {
    }

    class NestedClassA {
        // 在嵌套类中无法访问外部类的属性和方法
        fun method() {
            // 以下2行编译错误
            // println(field)
            // outerMethod()

            // 只能访问另一个嵌套类
            val nestedB = NestedClassB()
            nestedB.method()
        }
    }

    class NestedClassB {
        fun method() {
            println("invoke NestedClassB method")
        }
    }
}
