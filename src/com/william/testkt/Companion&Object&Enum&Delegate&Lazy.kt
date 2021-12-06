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

import kotlin.properties.Delegates
import kotlin.reflect.KProperty


fun main() {
    // 一、kotlin 伴生对象
    // 为外部类模拟生成静态成员，可以使用外部类的类名直接调用伴生对象的属性或方法
    val demo = Demo()
    println(demo.field)
    // 调用伴生对象的属性和方法
    Demo.KEY_NAME
    Demo.method()

    // 二、kotlin 对象声明和单例模式
    // 使用 object 声明对象表达式
    val runnable = object : Runnable {
        override fun run() {
            println("run in Runnable")
        }
    }
    // 同上，lambda 简写形式
    val runnable2 = Runnable {
        println("run in Runnable lambda")
    }

    Thread(runnable).start()
    Thread(runnable2).start()

    // 调用单例类的属性和方法
    println(DemoManager.field)
    DemoManager.method()


    // 三、kotlin 枚举

    // 构造枚举
    val tuesday = Week.valueOf("Tuesday")
    // 访问枚举常量的名称，与在其枚举声明中声明的完全相同。
    println(tuesday.name) // Tuesday
    // 返回此枚举常量的序数（它在枚举声明中的位置，其中初始常量被分配零序数）
    println(tuesday.ordinal) // 1
    // 返回此枚举类型的常量的数组
    println(Week.values().contentToString()) // [Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday]

    // 访问枚举
    val week: Week = Week.Monday
    when (week) {
        Week.Monday -> println(week.name) // Monday
        Week.Tuesday -> println(week.name)
        Week.Wednesday -> println(week.name)
        Week.Thursday -> println(week.name)
        Week.Friday -> println(week.name)
        Week.Saturday -> println(week.name)
        Week.Sunday -> println(week.name)
    }


    // 四、kotlin 委托
    // 类委托
    val agent1 = Agent1(FoodFactory())
    agent1.produce() // 生产美食
    val agent2 = Agent2()
    agent2.produce() // Agent2 自己 生产美食

    // 属性委托
    val fieldDemo = FieldDemo()
    fieldDemo.name = "test field delegate" // 设置 FieldDemo 的属性 name 值：test field delegate
    println(fieldDemo.name) // 获取 FieldDemo 的属性 name 值

    // map 委托
    // 只读属性对外暴露 Map
    val delegate1 = DelegateMap(mapOf("key1" to 1, "key2" to "str", "key3" to true))
    // 相当于调用 ReadOnlyProperty 的 getValue 方法
    println(delegate1.key1)
    println(delegate1.key2)
    println(delegate1.key3)

    println("-----111------")

    val map = delegate1.map
    println(map["key1"])
    println(map["key2"])
    println(map["key3"])

    println("-----------")

    // 读写属性对外暴露 MutableMap
    val delegate2 = DelegateMutableMap(mutableMapOf())
    // 相当于调用 ReadWriteProperty 的 setValue 方法
    delegate2.key1 = 100
    delegate2.key2 = "key2 value"
    delegate2.key3 = false

    println("-----222------")

    val mutableMap = delegate2.map
    println(mutableMap["key1"])
    println(mutableMap["key2"])
    println(mutableMap["key3"])

    // 五、kotlin 延迟属性

    // 只会打印一次 "执行 lambda 表达式"
    println(lazyField)
    println(lazyField)


    // 六、kotlin 属性监听
    observerField1 = "set new value" // kotlin.String 的 oldValue: default, newValue: set new value

    observerField2 = 1 // kotlin.Int 的 oldValue: 0, newValue: 1
    observerField2 = -1 // kotlin.Int 的 oldValue: 1, newValue: -1，此处会设置失败，需要大于0才能设置成功
    println(observerField2) // 1

}

// 延迟属性：只会获取一次 lambda 表达式的值，后续会使用第一次获取的值直接返回
val lazyField: String by lazy {
    println("执行 lambda 表达式")
    "this is a lazy field"
}

// 传入策略，使用锁用于确保只有单个线程可以初始化Lazy实例。
val lazyField2: String by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
    "this is a lazy field"
}

/**
 * 只读属性可以委托给 Map
 * 类只需要对外暴露 map 即可
 */
class DelegateMap(val map: Map<String, Any?>) {
    val key1: Int by map
    val key2: String by map
    val key3: Boolean by map
}

/**
 * 读写属性可以委托给 MutableMap
 * 类只需要对外暴露 map 即可
 */
class DelegateMutableMap(val map: MutableMap<String, Any?>) {
    var key1: Int by map
    var key2: String by map
    var key3: Boolean by map
}

class Demo {
    val field: String = "demo field"

    // 伴生对象
    companion object {
        // 定义常量
        const val KEY_NAME = "key_name"

        fun method() {
            println("invoke companion method")
        }
    }
}

/**
 * kotlin 使用 object 定义单例类
 */
object DemoManager {

    var field = "string value"

    fun method() {
        println("invoke method")
    }
}

/**
 * 定义枚举
 */
enum class Week(val text: String) {
    Monday("星期一"),
    Tuesday("星期二"),
    Wednesday("星期三"),
    Thursday("星期四"),
    Friday("星期五"),
    Saturday("星期六"),
    Sunday("星期日")
}


/**
 * kotlin 定义接口
 */
interface Factory {
    fun produce()
}

/**
 * 美食工厂，实现工厂的方法
 */
class FoodFactory : Factory {
    override fun produce() {
        println("生产美食")
    }
}

/**
 * Agent1 实现了 Factory 接口，但委托给 FoodFactory 去实现
 */
class Agent1(factory: FoodFactory) : Factory by factory

/**
 * Agent2 实现了 Factory 接口，但委托给 FoodFactory 去实现，重写了接口定义的方法
 */
class Agent2 : Factory by FoodFactory() {
    override fun produce() {
        println("Agent2 自己 生产美食")
    }
}


/**
 * kotlin 定义属性委托
 */
class FieldDemo {
    var name: String by FieldDelegate()
}

/**
 * ReadWriteProperty：可用于实现读写属性的属性委托的基本接口。
 * 这只是为了方便； 只要您的属性委托具有具有相同签名的方法，您就不必扩展此接口。
 */
class FieldDelegate {
    private var fieldValue = "default str"

    operator fun getValue(thisRef: FieldDemo, property: KProperty<*>): String {
        println("获取 ${thisRef.javaClass.simpleName} 的属性 ${property.name} 值")
        return fieldValue
    }

    operator fun setValue(thisRef: FieldDemo, property: KProperty<*>, value: String) {
        println("设置 ${thisRef.javaClass.simpleName} 的属性 ${property.name} 值：$value")
        fieldValue = value
    }
}

/**
 * 属性监听，总是能赋值成功
 */
var observerField1: String by Delegates.observable("default") { property, oldValue, newValue ->
    println("$property 的 oldValue: $oldValue, newValue: $newValue")
}

/**
 * 属性监听，符合条件，才能成功赋值
 */
var observerField2: Int by Delegates.vetoable(0) { property, oldValue, newValue ->
    println("$property 的 oldValue: $oldValue, newValue: $newValue")
    newValue > 0
}


/**
 * kotlin 定义密封类，本质上是抽象类
 */
sealed class Result<out T> {
    companion object {
        fun <T> onSuccess(data: T): Result<T> = Success(data)
        fun <T> onError(error: Exception): Result<T> = Error(error)
    }
}

data class Success<out T>(val data: T) : Result<T>()
data class Error(val error: Exception) : Result<Nothing>()
