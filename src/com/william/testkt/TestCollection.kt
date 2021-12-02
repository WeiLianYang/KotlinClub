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


fun main() {
    // 一、kotlin set 集合
    // 1. kotlin 创建 set 集合

    // 创建不可变集合
    val set1 = setOf(1, 2, 3)

    // 创建可变集合
    val set2 = mutableSetOf(1, 2, 3)

    // 创建 HashSet 集合
    val set3 = hashSetOf(1, 2, 3)

    // 创建 LinkedHashSet
    val set4 = linkedSetOf(1, 2, 3)

    // 创建 TreeSet
    val set5 = sortedSetOf(1, 2, 0, -1)

    // 2. kotlin Set 常用方法

    val set = setOf("kotlin", "java", "swift", "flutter")
    // set 转 map
    val setToMap = set.associateBy { "${it.length}" }
    println(setToMap) // {6=kotlin, 4=java, 5=swift, 7=flutter}

    // set 判断包含关系
    set.contains("kotlin")
    println("kotlin" in set) // true
    println("swift" !in set) // false

    // 获取删除 set 集合前面 n 个元素的集合
    println(set.drop(3)) // [flutter]

    // 过滤 set 中含有 ft 的元素集合
    println(set.filter { "ft" in it }) // [swift]

    // 找出 set 中含有 ft 的元素，找不到就是null
    println(set.find { "ft" in it }) // swift
    println(set.find { "python" in it }) // null

    // 拼接集合中的元素
    println(set.fold("") { acc, ele -> acc + ele }) // kotlinjavaswiftflutter

    // 查找元素索引
    println(set.indexOf("kotlin")) // 0

    // 获取最大值，最小值
    println(set.maxOrNull()) // swift
    println(set.minOrNull()) // flutter

    // 反转集合
    println(set.reversed()) // [flutter, swift, java, kotlin]

    val setStr = setOf("flutter", "oc")
    // 计算2个集合的交集
    println(set intersect setStr) // [flutter]

    // 计算2个集合的并集
    println(set union setStr) // [kotlin, java, swift, flutter, oc]

    // 3. kotlin Set 遍历
    for (item in set) {
        println(item)
    }

    for (index in set.indices) {
        println(set.elementAt(index))
    }

    set.forEach {
        println(it)
    }

    // 4. kotlin 可变 set 集合
    val mutableSet = mutableSetOf("1", "2")

    // 添加元素
    mutableSet.add("3")
    mutableSet.addAll(setOf("4", "5", "6"))

    // 删除元素
    mutableSet.remove("6")
    mutableSet.removeAll(setOf("4", "5"))

    println(mutableSet) // [1, 2, 3]


    // 二、kotlin List 集合
    // 创建不可变集合
    val list1 = listOf(1, 2, 3)
    val list2 = listOfNotNull("1", "2", null)
    println(list2) // [1, 2] 即使传入了null值，返回的集合也不会包含

    // 创建可变集合
    val list3 = mutableListOf(1, 2, 3, 2)
    val list4 = arrayListOf(1, 2, 3)

    // 读取集合元素
    println(list1.get(0)) // 1
    println(list4[0]) // 1

    // 获取索引
    println(list2.indexOf("2")) // 1
    println(list3.lastIndexOf(2)) // 3

    // 截取子集
    val sub = list3.subList(0, 2)
    println(sub) // [1, 2]

    // 添加元素
    list4.add(4)
    list4.add(0, 1)
    // 具名函数，移除指定元素
    list4.remove(element = 4)
    // 删除索引为 0 的元素
    list4.removeAt(2)

    println(list4) // [1, 1, 3]

    // 三、kotlin map 集合
    // 创建 map 不可变集合
    val map = mapOf("key0" to 0, "key1" to 1)

    // 创建 map 可变集合
    val map2 = mutableMapOf("key0" to 0, "key1" to 1)
    val map3 = hashMapOf("key0" to 0, "key1" to 1)
    val map4 = linkedMapOf("key0" to 0, "key1" to 1)
    val map5 = sortedMapOf("key0" to 0, "key1" to 1)

    // 遍历 map
    for (en in map.entries) {
        println("key: ${en.key}, value: ${en.value}")
    }
    for (key in map.keys) {
        println("key: ${key}, value: ${map[key]}")
    }
    for ((key, value) in map) {
        println("key: $key, value: $value")
    }
    map.forEach { (key, value) ->
        println("key: $key, value: $value")
    }

    // map 添加删除元素
    map2.put("key2", 2)
    map3["key3"] = 3
    map4.remove("key1")
    map5.clear()

}