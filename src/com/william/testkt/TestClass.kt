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
 * Kotlin 定义类 Phone，使用 open 修饰类，表明可被继承
 * 主构造器参数 为 system 只读属性，可被覆写
 * 所有属性均会自动生成 getter 或 setter 方法，val 修饰的只有 getter 方法
 */
open class Phone constructor(open val system: String = "") {
    // 读写属性，类型自动推断为String
    var name: String = ""
    var price: Int = 0

    // 次构造器，调用主构造器传值
    constructor(system: String, name: String, price: Int) : this(system) {
        // 冒号后面的 this 是对类主构造器的调用，对 system 赋值
        // 下面的 this 是对当前类实例的引用，同 Swift 使用 self 调用自身属性赋值一样
        this.name = name
        this.price = price
    }

    // 打印属性值
    fun printField() {
        println("system: $system, name: $name, price: $price")
    }

    // 实例调用 println() 时会打印这个方法的返回值
    override fun toString(): String {
        return "Phone{system: $system, name: $name, price: $price}"
    }

    /**
     * 父类初始化块
     */
    init {
        // 可在初始化块中访问主构造器的参数
        println("phone init: $system")
    }
}

/**
 * 继承 Phone，覆写其中属性
 */
class Android : Phone() {
    // 覆写 system 属性
    override val system: String = "android"

    /**
     * 子类初始化块
     */
    init {
        println("android init")
    }
}

class iOS : Phone() {
    // 覆写 system 属性
    override val system: String = "iOS"
}


/**
 * 定义儿童类
 */
class Kid {

    /**
     * 会自动生成 getter 或 setter 方法，无需像 java 一样手动生成
     */
    var name: String = ""

    /**
     * 使用 get set 方法对属性赋值，类似于 swift
     * 传值名称默认为 value，可自定义。使用 field 引用幕后字段赋值
     */
    var age: Int = 0
        get() {
            return 18
        }
        set(value) {
            if (value > 18) {
                println("超出了儿童年龄")
            } else {
                field = value
            }
        }

    /**
     * 使用 private 修饰的属性是幕后属性，没有幕后字段。不会自动生成 getter 或 setter 方法
     * 如果外部要访问，需要手动提供 getter 方法
     */
    private var sex = "male"

    override fun toString(): String {
        return "Kid{name: $name, age: $age}"
    }
}

/**
 * 定义类，kotlin 使用 lateinit 定义延迟初始化属性
 */
class Teacher {

    // kotlin 使用 lateinit 修饰：当已知属性不会为空时，但在程序上下文中属于延后初始化值
    // 不需要赋初始值。如果在初始化之前，访问该属性，会报 属性还未初始化的异常
    lateinit var name: String
}


/**
 * 定义 componentN 方法用于解构
 */
class ComponentClass(
    var system: String = "",
    var name: String = "",
    var price: Int = 0
) {
    operator fun component1(): String {
        return system
    }

    operator fun component2(): String {
        return name
    }

    operator fun component3(): Int {
        return price
    }
}


/**
 * kotlin 定义数据类，会自动生成含有属性各种组合的构造器
 * 会为每个属性生成 componentN() 方法
 * 主构造器至少含有一个参数
 */
data class PhoneData(
    var system: String = "",
    var name: String = "",
    var price: Int = 0
)


fun main() {
    // 1. kotlin 定义类
    // 创建 Phone 类的实例
    val phone = Phone("ios", "iPhone 13", 5999) // phone init: ios
    phone.printField() // system: ios, name: iPhone 13, price: 5999
    println(phone) // Phone{system: ios, name: iPhone 13, price: 5999}

    // 创建 Android 类的实例
    val android = Android() // phone init: null android init
    android.name = "xiaomi"
    android.price = 3999
    android.printField() // system: android, name: xiaomi, price: 3999
    println(android) // Phone{system: android, name: xiaomi, price: 3999}

    // 2. kotlin 属性的 setter 和 getter 方法
    val kid = Kid()
    kid.name = "Tom"
    kid.age = 14
    println(kid)

    val teacher = Teacher()
    teacher.name = "David"

    // 3. kotlin componentN 方法和解构
    // kotlin 解构，利用 component1, component2 方法将实例赋值给2个变量
    val comIns1 = ComponentClass("harmony", "huawei", 3999)
    val (system1, name1) = comIns1
    println("system: $system1, name: $name1") // system: harmony, name: huawei

    // kotlin 解构，利用 component1, component2, component3 方法将实例赋值给3个变量
    val comIns2 = ComponentClass("ios", "iphone 12", 4999)
    val (system2, name2, price2) = comIns2
    println("system: $system2, name: $name2, price: $price2") // system: ios, name: iphone 12, price: 4999

    // 不需要使用某变量时，使用 _ 来占位
    val comIns3 = ComponentClass("ios", "iphone 8", 1999)
    val (_, _, price) = comIns3
    println("price: $price") // price: 1999


    // 使用数据类的构造器初始化实例
    // 调用默认的无参主构造器
    // 1. 调用默认的无参主构造器
    val data1 = PhoneData()
    // 2. 调用含有1个参数的构造器
    val data2 = PhoneData("android")
    // 3. 调用含有2个参数的构造器
    val data3 = PhoneData("android", "meizu")
    // 4. 调用含有3个参数的构造器
    val data4 = PhoneData("data_android", "data_xiaomi", 4999)

    val (data_system, data_name, data_price) = data4
    println("system: $data_system, name: $data_name, price: $data_price") // system: data_android, name: data_xiaomi, price: 4999


    // 使用 is 检查类型
    val base = Base()
    val sub1: Sub = Sub()
    val sub2: Base = Sub()
    println(sub1 is Sub) // true
    println(sub1 is Base) // true
    println(base is Sub) // false


    // 使用 as as? 转换
    val cla1 = sub1 as Base // 子类实例 转为 父类型，转换成功
    val cla2 = sub2 as Base // 类型为父类型，引用实例的是子类的实例，将其转为父类型，可以转换成功
    val cla3 = base as? Sub // 父类实例 转为 子类型，报转换异常，需要使用 as? 进行安全转换，转换结果为 null

}

// 父类
open class Base

// 子类
class Sub : Base()
