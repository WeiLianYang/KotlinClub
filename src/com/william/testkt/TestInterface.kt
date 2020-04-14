package com.william.testkt

interface TestInterface {

    fun foo1()

    fun foo2() {
        println("I'm TestInterface foo2")
    }


}

class Child : TestInterface {
    override fun foo1() {
        // 方法体
        println("I'm child function foo1")
    }

    override fun foo2() {
        println("I'm child function foo2")
    }
}

// 接口中的属性只能是抽象的，不允许初始化值，接口不会保存属性值，实现接口时，必须重写属性：
interface MyInterface {
    var name: String // name 属性, 抽象的
}

class MyImpl : MyInterface {
    override var name: String = "3333" //重写属性
}

// 实现多个接口时，可能会遇到同一方法继承多个实现的问题
interface AA {
    fun foo() {
        println("A-foo")
    }

    // 未实现，没有方法体，是抽象的
    fun bar()
}

interface BB {
    fun foo() {
        println("B-foo")
    }

    fun bar() {
        println("b-bar")
    }
}

class CC : AA {
    override fun bar() {
        println("cc--bar")
    }
}

class D : AA, BB {
    override fun foo() {
        super<AA>.foo()
        super<BB>.foo()
    }

    override fun bar() {
        super<BB>.bar()
    }
}

fun main(args: Array<String>) {
    val child = Child()
    child.foo1()
    child.foo2()

    val impl = MyImpl()
    println(impl.name)

    val d = D()
    d.foo();
    d.bar();
}