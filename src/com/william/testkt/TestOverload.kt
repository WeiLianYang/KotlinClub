package com.william.testkt

class TestOverload {

    
}

class Greeting {

//    @JvmOverloads
    fun sayHello(prefix: String? = "Mr.", name: String) {
        println("Hello, $prefix $name")
    }
}

