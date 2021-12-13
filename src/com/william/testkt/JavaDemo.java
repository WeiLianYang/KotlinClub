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

package com.william.testkt;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

import java.util.Arrays;

public class JavaDemo {

    String is;

    public String getIs() {
        return is;
    }

    public void setIs(String is) {
        this.is = is;
    }

    public void in() {
        System.out.println("call java method in");
    }

    public void multiParams(int... params) {
        System.out.println(Arrays.toString(params));
    }

    public static void main(String[] args) {
        // 二、Java 调用 Kotlin
        // 1. 调用 kotlin 类的属性和方法
        KotlinDemo demo = new KotlinDemo();
        System.out.println(demo.getField()); // kotlin field
        demo.method(); // call kotlin method

        // 2. 调用 kotlin 的顶级属性和顶级函数
        // 使用 "文件名" 加 "Kt' 后缀 加 "." 调用
        TestCallEachOtherKt.getTopLevelField(); // call kotlin top level field
        TestCallEachOtherKt.topLevelFunc(); // call kotlin top level function

        // 3. 调用生成指定类名的 kotlin 属性和函数
        System.out.println(CallKtDemo.getField());
        CallKtDemo.method();

        // 4. 调用 kotlin 的伴生对象的方法和静态方法
        TestCallKotlin.Companion.method1();
        TestCallKotlin.method2();

        // 5. 调用 kotlin 的单例类的方法
        KotlinObject.INSTANCE.method1();
        KotlinObject.method2();

        // 6. 调用 kotlin 的 lambda

        // 参数为 lambda 表达式，入参为 Int，返回值为 Unit
        KotlinObject.method3(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                System.out.println(integer);
                return null;
            }
        });

        // 参数为 lambda 表达式，入参为 Boolean，返回值为 String
        KotlinObject.method4(new Function1<Boolean, String>() {
            @Override
            public String invoke(Boolean param) {
                System.out.println("invoke param: " + param);
                return param ? "success" : "failed";
            }
        });

        // 参数为 lambda 表达式，无入参，返回值为 Unit
        KotlinObject.method5(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                System.out.println("invoke method5 no params");
                return null;
            }
        });

        // 参数为 lambda 表达式，入参为 Int, String，返回值为 Boolean
        KotlinObject.method6(new Function2<Integer, String, Boolean>() {
            @Override
            public Boolean invoke(Integer integer, String s) {
                System.out.println("invoke method6 params:integer=" + integer + ", s=" + s);
                return integer > 0;
            }
        });

    }

}
