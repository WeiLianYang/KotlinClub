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
    testSeq()

    testList()
}

// 惰性集合操作
fun testSeq() {
    val sequence = sequenceOf(1, 2, 3, 4)
    val result: Sequence<Int> = sequence
            .map { i ->
                println("Map $i")
                i * 2
            }
            .filter { i ->
                println("Filter $i")
                i % 3 == 0
            }
//取出元素 1 -> map 为 2 -> filter 判断 2 是否能被 3 整除
//取出元素 2 -> map 为 4 -> filter 判断 4 是否能被 3 整除
//...
//惰性指当出现满足条件的第一个元素的时候，Sequence 就不会执行后面的元素遍历了，即跳过了 4 的遍历。

//    Sequence 这种类似懒加载的实现有下面这些优点：
//    一旦满足遍历退出的条件，就可以省略后续不必要的遍历过程。
//    像 List 这种实现 Iterable 接口的集合类，每调用一次函数就会生成一个新的 Iterable，下一个函数再基于新的 Iterable 执行，
//    每次函数调用产生的临时 Iterable 会导致额外的内存消耗，而 Sequence 在整个流程中只有一个。
//    因此，Sequence 这种数据类型可以在数据量比较大或者数据量未知的时候，作为流式处理的解决方案。
    println(result.first())
}

fun testList() {
    val list = listOf(1, 2, 3, 4)
    val result: List<Int> = list
            .map { i ->
                println("Map $i")
                i * 2
            }
            .filter { i ->
                println("Filter $i")
                i % 3 == 0
            }

    println(result.first())
}