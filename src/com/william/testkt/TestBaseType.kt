package com.william.testkt

/**
 * Kotlin基础数据类型
 */
fun main(args: Array<String>) {
    // 类型扩展
    var aaa: Int? = null
    val bbb: Int = 22
    println(aaa)//null

    // 编译为基础类型int
    val in1: Int = 200
    val in2: Int = 200
    println(in1 === in2)// true

    // 编译为Integer类型
    val intg1: Int? = 200
    val intg2: Int? = 200
    println(intg1 === intg2)// false，若在byte取值范围内为true

    // 二进制
    val bval1 = 0b101010
    val bval2 = 0B1010110
    var x = -0b11111111
    println(x)

    // 十进制
    val tenVal1 = 0x132
    val tenVal2 = 0X1d

    // 字符串模板
    println("bval1的值为：${bval1}")// bval1的值为：42
    println("bval2的值为：${bval2}")// bval2的值为：86
    println("tenVal1的值为：${tenVal1}")// tenVal1的值为：306
    println("tenVal2的值为：${tenVal2}")// tenVal2的值为：29

    // 分隔符增加可读性
    val spilt1 = 1_000_000_000
    val spilt2 = 123_234_234
    val spilt3 = 1234_1234
    println("${spilt1}，${spilt2}，${spilt3}")// 1000000000，123234234，12341234

    val f1 = 5.2345556f
    // 下面将看到af1的值已经发生了改变
    println("f1的值为：${f1}")// f1的值为：5.2345557

    // 编译报错，因为默认值的类型为Double
//    var f2: Float = 24.22
    val d2 = 3.22e2
    println("d2的值为：${d2}")// 322.0

    val a = 0.0
    // 正无穷大
    println("2.0/a的值为：${2.0 / a}，${-2.0 / a}")// Infinity，-Infinity
    // 所有正无穷大数值都相等
    println(2.0 / a == 20000.0 / 0.0)// true
    // 0.0/0.0结果为非数
    val nan: Double = a / a
    println("a/a的值为：${nan}")// NaN
    // 非数与自己都不想等
    println(nan == nan)// false

    // 字符型
    // 直接指定单个字符作为字符值
    val aChar: Char = 'a'
    // 使用转义字符作为字符值
    val covChar: Char = '\r'
    // 使用Unicode编码值来指定字符值
    val ch: Char = '\u5475'
    // 将输出一个呵字符
    println(ch) // 呵
    // 定义一个'呵'字字符值
    val hh: Char = '呵'
    // 将Char型变量当成Int型处理会报错
//    var hhVal: Int = hh

    // 所有数值类型必须显示转换，不能隐式转换
    var b_val: Byte = 70
    var s_val: Short = 110
    // 隐式转换报错，小转大
//    var b : Short = b_val
    // 必须显示转换
    var b: Short = b_val.toShort()
    var c: Byte = s_val.toByte()
    println("b:${b}, c:${c}")// b:70, c:110
    val bb = 230
    val cc: Byte = bb.toByte()
    println(cc)// -26

    // 虽然缺乏隐式转换，但是在表达式中会基于上下文推断自动切换
    var b_s = b_val + s_val
    println("b_s的值为：${b_s}")// b_s的值为：180
    // b_s的值映射为java int
    println("b_s的类型为：${b_s.javaClass}")// b_s的类型为：int
    // 下面表达式中的b_val强转为Long
    val l_bs = b_val.toLong() + s_val.toByte()
    println("l_bs的值为:${l_bs}，类型为:${l_bs.javaClass}")// l_bs的值为:180，类型为:long

    // toChar()
    var result = ""
    // 循环3次
    for (i in 0..2) {
        // 生成一个26-122之间的Int类型的整数
        val intVal = (Math.random() * 26 + 97).toInt()
        // 强转intVal为Char型 拼接result
        result = result + intVal.toChar()
//        result += intVal.toChar()
    }
    println(result)// 随机结果

    // Char型值加减一个整数得到Char型值
    var c1 = 'h'
    var c2 = 'j'
    println(c1 + 4)// 输出l
    println(c1 - 4)// 输出d
    // 2个Char型值相减得到整型值，不能相加
    println(c1 - c2)// 输出-2

    // 浮点型之间的显示转换
    var f_: Float = 2.3f
    var d_: Double = 4.5
    // 强转为Double才能赋值给d
    var d: Double = f_.toDouble()
    println("d的值为: ${d}")// 2.299999952316284

    // 强转Float， 表达式推断类型
    var f_1 = f_ * d_.toFloat()
    // 推算结果为等级最高的类型
    // Double
    var d_2 = f_ * d_
    var other: Int = 5
    // Double
    var totald_ = d_ * other
    // Int
    var totald_2 = d_.toInt() * other
    // f_1的类型为：float，d_2的类型为：double，totald_的类型为: double，totald_2的类型为: int
    println("f_1的类型为：${f_1.javaClass}，" +
            "d_2的类型为：${d_2.javaClass}，" +
            "totald_的类型为: ${totald_.javaClass}，" +
            "totald_2的类型为: ${totald_2.javaClass}")
    // 表达式类型自动提升
    var s_val2: Short = 20
    // 右边为Int行，左边为Short型，编译错误
//    s_val2 = s_val2 - 2
    // 正确的表达式类型提升
    var f: Byte = 40
    var g: Short = 90
    var i: Int = 20
    var m: Double = .3
    // 右边表达式最高等级的操作数为m，即右边结果为Double类型，dddd结果即为Double型
    var dddd = f + g + i * m
    println(dddd)// 136.0

    // 同java ，int相除除不尽取整
    println(8 / 3)// 2

    // + 加号连接符和运算符
    println("android__" + 'a' + 88)// android__a88
    println('a' + 2 + "__android")// c__android

    // Boolean 型
    var boo1: Boolean = true
    var boo2: Boolean = false
    println("boo1:${boo1}, boo2:${boo2}")// boo1:true, boo2:false


    // null安全，可空和非空
    var string = "kotlin"
    // 因为可能会转换失败返回null，所以不能赋值给Int型，编译错误
//    var number1: Int = string.toIntOrNull()
    // 编译OK
    var number2: Int? = string.toIntOrNull()
    // 也可以这样，自动推断类型为Int?
    var number3 = string.toIntOrNull()

    var str1: String = "android"
    var str2: String? = "Kotlin"
    // 编译错误
//    str1 = null
    str2 = null// OK
    // 因为str1不可能为Null，所以不会发生空指针
    println(str1.length)
    // 编译错误，因为str2可空，需要先判断才能访问属性和方法
//    println(str2.length)

    // 先判断非空，再访问str2的属性
    var length = if (str2 != null) str2.length else -1
    println("str2的长度为：${length}")// -1
    // 必须先判断才能访问属性和方法，不然编译错误
    if (str2 != null && str2.length > 0) {
        println(str2.length)
    } else {
        println("空字符串")
    }

    var booVal: Boolean? = null
    if (booVal == true) {
        println(true)
    }
    // 条件表达式的结果必须为Boolean型，下面会编译错误
//    if (booVal) {
//    }

    // 安全调用
    var security: String? = "security"
    println(security?.length)// 输出8
    security = null
    println(security?.length)// 输出null

    // 安全调用结合let
    var array: Array<String?> = arrayOf("android,", null, "kotlin,", "ios")
    for (ele in array) {
        ele?.let { println(it) }// android,kotlin,ios
    }

    var elvisStr: String? = "kotlin"
    var length1 = if (elvisStr != null) elvisStr.length else -1
    println(length1)// 输出6
    elvisStr = null
    // Elvis运算符，简化的if-else
    // 定义：只要 "?:" 左边的表达式结果为不为null，则返回左边表达式的值，否则返回 "?:" 右边表达式的值
    var length2 = elvisStr?.length ?: -1
    println(length2)// -1

    // 强制调用 !!. 可能会发生空指针异常
    var i_val: String? = "100"
    println(i_val!!.length)
    i_val = null
//    println(i_val!!.length)// 空指针异常，编译OK
    var array2: Array<String?> = arrayOf("android22", null, "kotlin")
    for (ss in array2) {
//        ss!!.let { println(it) }// 第二个元素会发生空指针异常
    }

    // 字符串
    // 普通字符串
    var str11 = "I love kotlin"
    println(str1[0])// I
    println(str1[2])// l
    // 遍历字符串，也可以通过索引获取
    for (s in str1) {
        print(s)
    }

    println(str11.length)
    // 原始字符串, "|"作为边界符，"|"之前的缩进都会去掉
    var str22 = """
        |I am a programmer,
        |26 years old,
        |172cm,
        |married
    """.trimMargin()
    println(str22)

    var str33 = """
        &I am a programmer,
        &26 years old,
        &172cm,
        &married
    """.trimMargin("&")
    /**
    android13
    I am a programmer,
    26 years old,
    172cm,
    married
     */
    println(str33)// 结果同上

    // 字符串模板允许嵌入变量或者表达式
    var question = 5
    println("第一题得分: ${question}")

    var intValue = java.util.Random().nextInt(20) + 80
    var resultVal = "随机数的值为：${intValue}"
    println(resultVal)

    var name = "William"
    var age = 26
    var height = 170
    var information = """
        |${name} is a programmer,
        |${age} years old,
        |${height + 2}cm,
        |married,
        |Learn kotlin ${java.util.Random().nextInt(30) + 10} days
    """.trimMargin()
    println(information)

    // Kotlin中的String
    var str_a = "3.1415926"
//    var int_str_a: Int = str_a.toInt() // NumberFormatException
    var d_str_a: Double = str_a.toDouble()
    var str_b = "314"
    var int_str_b: Int = str_b.toInt()
    println("${d_str_a}, ${int_str_b}")//3.1415926, 314

    // 首字母大小写
    val str_d = "kotlin.kt"
    println(str_d.capitalize())// 首字母大写 Kotlin.kt
    println(str_d.decapitalize())// 首字母小写 kotlin.kt

    // 取相同前缀
    println(str_d.commonPrefixWith("kotlin.java"))// kotlin.
    // 取相同后缀
    println(str_d.commonSuffixWith("HelloWorld.kt"))// .kt

    var str_e = "kotlin666"
    // 判断str_e是否包含2个连续的数字，正则表达式匹配
    println(str_e.contains(Regex("\\d{2}")))// true


}