package com.william.testkt

/**
 * @author : WilliamYang
 * @date : 2021/11/29 18:07
 * @description : 流程控制
 */

fun main(args: Array<String>) {

    // 一、kotlin if-else 判断结构
    // 1. if-else 简单判断
    val bool = true
    if (bool) {
        println("bool is true")
    } else {
        println("bool is false")
    }

    // 2. if-else-if 判断结构
    val score = 85
    if (score > 90) {
        println("score > 90，优秀")
    } else if (score > 80) {
        println("score > 80，良好")
    } else if (score > 60) {
        println("score > 60，及格")
    }

    // 3. if 作为表达式返回结果
    var result = if (score > 90) {
        "优秀"
    } else if (score > 80) {
        "良好"
    } else if (score > 60) {
        "及格"
    } else {
        "不及格"
    }
    println(result) // 良好

    println("-----------------------------")

    // 二、kotlin when 结构语句
    // 1. when 分支结构匹配
    result = when (score) {
        100 -> {
            "满分"
        }
        60 -> {
            "刚好及格"
        }
        else -> {
            "$score，你猜是啥范围"
        }
    }
    println(result) // 85，你猜是啥范围

    // 2. when-in 范围分支匹配
    result = when (score) {
        in 80..100 -> {
            "超过了一般小朋友"
        }
        in 60..80 -> {
            "及格了哦"
        }
        else -> {
            "不及格"
        }
    }
    println(result) // 超过了一般小朋友

    // 3. when 条件分支匹配
    result = when {
        score > 90 -> {
            "优秀"
        }
        score > 80 -> {
            "良好"
        }
        score > 60 -> {
            "及格"
        }
        else -> {
            "不及格"
        }
    }
    println(result) // 良好


    val anyObj: Any = 100
    // 4. when 类型匹配
    result = when (anyObj) {
        is Int -> {
            "anyObj is int type"
        }
        else -> {
            "anyObj is other type"
        }
    }
    println(result) // anyObj is int type

    println("-----------------------------")

    // 三、kotlin 循环结构
    // 1. kotlin while 循环 ：每次执行循环体之前，会先判断循环条件
    var count = 0
    while (count < 5) {
        count++
        print(count) // 12345
    }
    println()

    // 2. kotlin do-while 循环，特点：每次执行完一次循环体后再判断循环条件
    do {
        count++
        print(count) // 678
    } while (count < 8)
    println()

    // 3. kotlin for-in 循环
    val list = arrayListOf("java", "kotlin", "swift")
    for (item in list) {
        println(item)
    }

    // 4. kotlin 嵌套循环
    val outerList = arrayListOf("outer0", "outer1", "outer2")
    val innerList = arrayListOf("inner0", "inner1", "inner2")
    for (outer in 0 until outerList.size) {
        for (inner in 0 until innerList.size) {
            println("${outerList[outer]}, ${innerList[inner]}")
        }
    }

    // 5. kotlin 控制循环结构
    // kotlin 使用 break 结束循环
    for (i in 0..2) {
        if (i == 1) {
            break
        }
    }

    // kotlin 结束外层循环 使用限定符标签 名称随意
    outer@ for (outer in 0 until outerList.size) {
        for (inner in 0 until innerList.size) {
            if (inner == 1) {
                break@outer
            }
            println("${outerList[outer]}, ${innerList[inner]}")
        }
    }

    // kotlin 使用 continue 跳过本次循环
    for (i in 0..2) {
        if (i == 1) {
            continue
        }
        println(i)
    }

    // kotlin 跳过外层循环 使用限定符标签 名称随意
    outer@ for (outer in 0 until outerList.size) {
        for (inner in 0 until innerList.size) {
            if (inner == 1) {
                continue@outer
            }
            println("${outerList[outer]}, ${innerList[inner]}")
        }
    }

    // kotlin 使用 return 结束循环，不论嵌套了多少层
    for (outer in 0 until outerList.size) {
        for (inner in 0 until innerList.size) {
            if (inner == 1) {
                return
            }
            println("${outerList[outer]}, ${innerList[inner]}")
        }
    }

}
