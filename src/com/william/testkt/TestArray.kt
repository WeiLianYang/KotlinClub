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


fun main(args: Array<String>) {
    // 1. kotlin 创建数组
    val arr1 = arrayOf(1, 2, 3)
    val arr2 = arrayOf("1", "2", "3")
    // 指定长度为 3 的整型数组，初始化值为 null
    val arr3 = arrayOfNulls<Int>(3)
    // 创建类型为 String 的空数组
    val emptyArr = emptyArray<String>()

    // 创建基本类型的数组
    val arrInt = intArrayOf(1, 2, 3)
    val arrByte = byteArrayOf(1, 2, 3)
    val arrInt2 = IntArray(3) { it * it } // 0 1 4


    // 2. kotlin 读取数组
    println(arr1[1])
    println(arr2.get(0)) // 和使用 [] 相同

    arrInt[0] = -1 // 修改第一个数为-1
    arrInt.set(0, -1) // 同上


    // 3. kotlin 遍历数组
    // for-in 循环遍历数组
    for (item in arrByte) {
        print(item)
    }

    println()

    // 使用数组索引遍历
    for (index in arrByte.indices) {
        print(arrByte[index])
    }

    println()

    // 同时访问数组的索引和元素
    for ((index, value) in arrByte.withIndex()) {
        print("index: $index, value: $value, ")
    }

    println()

    // 4. kotlin 数组常用方法
    // 数组转集合
    val list = arrInt.asList() // [-1, 2, 3]
    // 数组转 Map
    val map1 = arr1.associate { Pair("key$it", it) } // {key1=1, key2=2, key3=3}
    val map2 = arr1.associateBy { "key$it" } // {key1=1, key2=2, key3=3}
    val map3 = arr1.associateTo(hashMapOf()) { Pair("key$it", it) } // {key1=1, key2=2, key3=3}
    val map4 = arr1.associateByTo(hashMapOf()) { "key$it" } // {key1=1, key2=2, key3=3}

    // 获取数值型数组的平均值
    val average = arr1.average() // 2.0

    // 获取数组的最大值和最小值
    val max = arr1.maxOrNull()
    val min = arr1.minOrNull()
    println("max: $max, min: $min") // max: 3, min: 1

    // 打印数组
    println(arr1.contentToString()) // [1, 2, 3]

    // 是否包含
    val result = arr1.contains(1) // true

    // 去掉数组重复元素
    val arrString = arrayOf("1", "1", "2")
    val unique = arrString.distinct() // [1, 2]

    // 复制数组，长度为4，超出的部分填充 null
    val copy = arr1.copyOf(4)
    println(copy.contentToString()) // [1, 2, 3, null]

    // 对数组排序
    val arr4 = intArrayOf(3, 1, 2, 5)
    arr4.sort()
    println(arr4.contentToString()) // [1, 2, 3, 5]

    // 获取元素在数组中的索引
    val index = arr1.indexOf(1)
    println(index) // 0

    // 5. 多维数组
    val multiArr = Array<IntArray>(3) { intArrayOf(1, 2) }
    multiArr.forEach { intArr ->
        println(intArr.contentToString())
        intArr.forEach { it ->
            println(it)
        }
    }

}