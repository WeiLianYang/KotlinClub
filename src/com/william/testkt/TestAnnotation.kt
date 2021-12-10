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


// 1. 定义注解
/**
 * 注解属性在使用时指定，其后不会再变，只能声明为只读属性
 */
annotation class Annotation1(val name: String, val desc: String)

annotation class Annotation2(val field1: Int, val field2: String)



// 2. 元注解
// 2.1 @Retention 修饰注解可以保留多长时间
// @Retention(value = AnnotationRetention.SOURCE) // 注解只保留在源代码中，注解会被编译器丢弃
// @Retention(value = AnnotationRetention.BINARY) // 注解将被记录在 class 文件中，JVM无法获取
@Retention(value = AnnotationRetention.RUNTIME) // 注解将被记录在 class 文件中，JVM可以获取，程序可以通过反射获取。默认值
annotation class Annotation3()

// 2.2 @Target 指定注解可以修饰的程序目标

// 注解只能修饰类
@Target(AnnotationTarget.CLASS)
annotation class Annotation4()

// 注解只能修饰注解
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class Annotation5()

// 注解只能修饰函数或方法，不含构造方法
@Target(AnnotationTarget.FUNCTION)
annotation class Annotation6()

// 注解只能修饰构造方法
@Target(AnnotationTarget.CONSTRUCTOR)
annotation class Annotation7()

// 注解只能修饰属性
@Target(AnnotationTarget.PROPERTY)
annotation class Annotation8()

// 注解只能修饰字段，包括属性的支持字段
@Target(AnnotationTarget.FIELD)
annotation class Annotation9()

// 注解只能修饰局部变量
@Target(AnnotationTarget.LOCAL_VARIABLE)
annotation class Annotation10()

// 注解只能修饰函数或构造函数的值参数
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Annotation11()

// 注解只能修饰表达式
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.EXPRESSION)
annotation class Annotation12()

// 注解只能修饰类型别名
@Target(AnnotationTarget.TYPEALIAS)
annotation class Annotation13()

// 指定多个可修饰的程序目标
@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION])
annotation class Annotation14()

// 2.3 可重复修饰的注解
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
annotation class RepeatAnnotation(val field1: Int, val field2: String)


class DemoAnnotation {

    @RepeatAnnotation(1, "2")
    @RepeatAnnotation(3, "4")
    fun method() {
    }
}


// 3. 使用注解

class TestAnnotation {

    @Annotation1(name = "method", desc = "this is a normal method")
    @Annotation2(field1 = 1001, field2 = "test annotation")
    fun method() {
        println("invoke method")
    }
}

fun main() {
    // 获取 TestAnnotation 的方法定义的注解信息
    val annotationList = TestAnnotation::method.annotations
    annotationList.forEach { annotation ->
        if (annotation is Annotation1) {
            println("name: ${annotation.name}, desc: ${annotation.desc}")
        } else if (annotation is Annotation2) {
            println("field1: ${annotation.field1}, field2: ${annotation.field2}")
        }
    }
}