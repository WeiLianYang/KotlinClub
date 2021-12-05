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



/**
 * kotlin 定义密封类，本质上是抽象类
 */
sealed class Result<out T> {
    companion object {
        fun <T> onSuccess(data: T): Result<T> = Success(data)
        fun <T> onError(error: Exception): Result<T> = Error(error)
    }
}

data class Success<out T>(val data: T) : Result<T>()
data class Error(val error: Exception) : Result<Nothing>()
