/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2018, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 * @author Yeeku.H.Lee kongyeeku@163.com<br>
 * @version 1.0<br>
 */
fun main(args: Array<String>) {
    // 定义匿名函数，赋值给test变量
    var test = fun(x: Int, y: Int): Int {
        return x + y
    }
    // 通过test调用匿名函数
    println(test(2, 4))//6
    var filteredList = listOf(3, 5, 20, 100, -25).filter(
            // 使用匿名函数作为filter()方法的参数
            fun(el): Boolean {
                return Math.abs(el) > 20
            })
    println(filteredList)

    // 定义匿名函数的函数体是单表达式，可以省略声明函数的返回值类型
    var wawa = fun(x: Int, y: Int) = x + y
    // 通过wawa调用匿名函数
    println(wawa(2, 4))
    var rt = listOf(3, 5, 20, 100, -25).filter(
            // 使用匿名函数作为filter()方法的参数
            // 匿名函数的函数体是单表达式，允许省略声明函数的返回值类型
            fun(el) = Math.abs(el) > 20
    )
    println(rt)
}