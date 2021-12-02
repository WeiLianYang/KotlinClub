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

class TestConstructor {

}

// 基类
open class Person(var name: String, var age: Int) {

}

// 如果子类有主构造函数， 则基类必须在主构造函数中立即初始化。
class Student(name: String, age: Int, var no: String, var score: Int) : Person(name, age) {


    class StudentB : Person {

        //如果子类没有主构造函数，则必须在每一个二级构造函数中用 super 关键字初始化基类，
        // 或者在代理另一个构造函数。初始化基类时，可以调用基类的不同构造方法。
        constructor(name: String) : super(name, 2) {
        }

        constructor(name: String, age: Int) : super(name, age) {
        }

    }

    /**用户基类**/
    open class Person2(name: String) {

        /**次级构造函数**/
        constructor(name: String, age: Int) : this(name) {
            //初始化
            println("-------基类次级构造函数---------")
        }

        open fun study() {
            println("hard hard study, day day up")
        }
    }

    /**子类继承 Person 类**/
    class StudentC : Person2 {

        /**次级构造函数**/
        constructor(name: String, age: Int, no: String, score: Int) : super(name, age) {
            println("-------继承类次级构造函数---------")
            println("学生名： $name")
            println("年龄： $age")
            println("学生号： $no")
            println("成绩： $score")
        }

        override fun study() {
            super.study()
            println("yes , I'm study hard and grows up quickly")
        }
    }

    // 如果有多个相同的方法（继承或者实现自其他类，如A、B类），
// 则必须要重写该方法，使用super范型去选择性地调用父类的实现。
    open class A {
        open fun f() {
            print("A")
        }

        fun a() {
            print("a")
        }
    }

    interface B {
        fun f() {
            println("B")
        }

        fun b() {
            println("b")
        }
    }

    class C() : A(), B {
        override fun f() {
            super<A>.f()// 调用 A.f()
            super<B>.f()// 调用 B.f()
        }
    }

    // 属性重写
    open class Foo {
        open val x: Int
            get() {
                return 2
            }
    }

    class Bar1 : Foo() {
        override var x: Int = 3
    }

    // 接口
    interface FooIf {
        val count: Int
    }


    // 可以在主构造函数中使用 override 关键字作为属性声明的一部分
    class Bar2(override val count: Int) : FooIf

    class Bar3 : FooIf {
        override var count: Int = 0
    }


    // 测试
    fun main(args: Array<String>) {
        val s = Student("Runoob", 18, "S12345", 89)
        println("学名： ${s.name}")
        println("年龄： ${s.age}")
        println("学号： ${s.no}")
        println("成绩： ${s.score}")

        println("--------------🔽--------------")

        val studentC = StudentC("William", 20, "S11111", 90)

        // 在基类中，使用fun声明函数时，此函数默认为final修饰，不能被子类重写。
        // 如果允许子类重写该函数，那么就要手动添加 open 修饰它, 子类重写方法使用 override 关键词
        studentC.study()

        println("--------------🔽--------------")
        val c = C()
        c.f();

        println("--------------🔽--------------")
        val bar1 = Bar1()
        println(bar1.x)
    }

}