# KotlinDemo

## 一、目录 

### 1. Kotlin 基本数据类型
- [Blog 介绍](https://blog.csdn.net/java_android_man/article/details/80243772)
- [Demo 示例](https://github.com/WeiLianYang/KotlinDemo/blob/master/src/com/william/testkt/TestBaseType.kt)


### 2. Kotlin 运算符
- [Blog 介绍](https://blog.csdn.net/java_android_man/article/details/121615270)
- [Demo 示例](https://github.com/WeiLianYang/KotlinDemo/blob/master/src/com/william/testkt/TestOperator.kt)


### 3. Kotlin 循环、流程控制：if-else、when、for-in
- [Blog 介绍](https://blog.csdn.net/java_android_man/article/details/121620253)
- [Demo 示例](https://github.com/WeiLianYang/KotlinDemo/blob/master/src/com/william/testkt/TestControl.kt)


### 4. Kotlin 数组及常用方法
- [Blog 介绍](https://blog.csdn.net/java_android_man/article/details/121643759)
- [Demo 示例](https://github.com/WeiLianYang/KotlinDemo/blob/master/src/com/william/testkt/TestArray.kt)

<br>

## 二、Kotlin项目开发规范总结：
#### 1. 返回数据模型中的字段除了数值型和Boolean以外，其他类型字段需要用可空声明；对于数值型和Boolean的字段可以定为不可空类型，但是需要赋值，方便在使用处调用。如果接口不返回这个字段，则GSON不会解析，如果字段返回null，则得到的是声明的初始值；
例如下面的数据类：
```kotlin
data class DataBean(
        var productid: String? = null,
        var imgurl: String? = null,
        var productname: String? = null,
        var averageprice: Double = 0.0,
        var rise: Boolean = false
)
```
#### 2. 扩展函数和扩展属性不要滥用，用的巧妙可以省很多模板代码；
例如下面获取dp和sp值的方式：
```kotlin
val Int.dp
    get() = this.toFloat().dp.toInt()

val Float.dp
    get() = getTypedValue(TypedValue.COMPLEX_UNIT_DIP, this)

val Float.sp
    get() = getTypedValue(TypedValue.COMPLEX_UNIT_SP, this)

private fun getTypedValue(unit: Int, value: Float): Float {
    return TypedValue.applyDimension(unit, value, Resources.getSystem().displayMetrics)
}
```
#### 3. 工具类建议写成顶级函数，以文件为单位归类，不需要对外暴露的用private修饰，避免污染调用；
例如图片加载的函数，可以直接写成下面的顶级函数：
```kotlin
/**
 * 加载图片
 * @param context 上下文
 * @param url 图片资源
 * @param imageView  目标ImageView
 * @param transType 转换类型
 * @param radius 半径，默认2
 * @param borderWith 外框宽度，默认0f，如果为0，底层设置会取1px（仅对api >= 25 有效）
 * @param borderColor 外框颜色，默认#e6e6e6（仅对api >= 25 有效）
 * @param cornerType 圆角类型，默认CornerType.ALL
 * @param blurred 模糊度,[1,25]，默认10
 * @param scale 缩放度，默认1即不缩放
 * @param errorResId 错误图片id，默认base_icon_loading
 * @param skipMemoryCache 跳过缓存，默认false
 * @param width The width in pixels to use to load the resource.
 * @param height The height in pixels to use to load the resource.
 * @param onResourceReady 加载就绪的回调函数
 * @param onLoadFailed 加载失败的回调函数
 */
fun loadImage(
    context: Any?,
    url: Any?,
    imageView: ImageView?,
    @TransformationType transType: Int = DEFAULT_TRANSFORMATION,
    radius: Int = 2,
    borderWith: Float = 0f,
    @ColorRes borderColor: Int = R.color.color_e6e6e6,
    cornerType: CornerType = CornerType.ALL,
    @IntRange(from = 1, to = 25) blurred: Int = 10,
    scale: Int = 1,
    @DrawableRes errorResId: Int = R.drawable.base_icon_loading,
    skipMemoryCache: Boolean = false,
    width: Int? = null,
    height: Int? = width,
    onResourceReady: OnGlideResourceReady? = null,
    onLoadFailed: OnGlideLoadFailed? = null
) {
    val recourse: Any? = when (url) {
        is String -> url
        is Int -> url
        is ByteArray -> url
        else -> null
    }
    val requestBuilder: RequestBuilder<Drawable>? = getRequestManager(
        context
    )
        ?.load(recourse)
        ?.apply(
            createOptions(
                transType, radius, borderWith, borderColor, cornerType,
                blurred, scale, errorResId, skipMemoryCache, width, height
            )
        )
        ?.transition(DrawableTransitionOptions.withCrossFade())
        ?.addListener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean
            ): Boolean {
                onLoadFailed?.invoke(e, model, target, isFirstResource)
                return false
            }
            
            override fun onResourceReady(
                resource: Drawable?, model: Any?, target: Target<Drawable?>?, dataSource: DataSource?, isFirstResource: Boolean
            ): Boolean {
                onResourceReady?.invoke(resource, model, target, dataSource, isFirstResource)
                return false
            }
        })
    // 可以只做图片加载监听，不一定非要加载到某个ImageView中
    imageView?.let {
        requestBuilder?.into(it)
    }
}

/**
 * 获取图片请求管理对象
 *
 * @param param context资源对象
 * @return RequestManager
 */
private fun getRequestManager(param: Any?): RequestManager? {
    param.let {
        return when (it) {
            is Activity -> {
                Glide.with(it)
            }
            is Fragment -> {
                Glide.with(it)
            }
            is View -> {
                Glide.with(it)
            }
            is Context -> {
                Glide.with(it)
            }
            else -> null
        }
    }
}

/**
 * 创建RequestOptions
 */
private fun createOptions(
    transType: Int,
    radius: Int,
    borderWith: Float,
    @ColorRes borderColor: Int,
    cornerType: CornerType,
    blurred: Int,
    scale: Int,
    errorResId: Int,
    skipMemoryCache: Boolean,
    width: Int?,
    height: Int?
): BaseRequestOptions<*> {
    val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .skipMemoryCache(skipMemoryCache)
        .error(errorResId)
    if (transType != TYPE_NORMAL) {
        val transformation = getMultiTransformation(
            transType, radius, borderWith,
            borderColor, cornerType, blurred, scale
        )
        transformation?.let {
            options.transform(it)
        }
        if (width != null && height != null) {
            return options.override(width, height)
        }
    }
    return options
}

/**
 * 创建MultiTransformation<Bitmap>，用于各种自定义效果
 */
private fun getMultiTransformation(
    transformationType: Int,
    radius: Int,
    borderWith: Float,
    @ColorRes borderColor: Int,
    cornerType: CornerType,
    blurred: Int,
    scale: Int
): MultiTransformation<Bitmap>? {
    val list = mutableListOf<Transformation<Bitmap>>()
    when (transformationType) {
        TYPE_CIRCLE -> list.add(CircleCrop())
        TYPE_CIRCLE_BORDER -> {
            list.apply {
                add(CircleCrop())
                add(
                    GlideCircleBorderTransform(
                        borderWith, BaseApp.instance.getColor(borderColor)
                    )
                )
            }
        }
        TYPE_ROUND_RECT -> {
            list.apply {
                add(CenterCrop())
                add(
                    GlideRoundRectBorderTransform(
                        borderWith, BaseApp.instance.getColor(borderColor), radius
                    )
                )
            }
        }
        TYPE_RADIUS -> {
            list.apply {
                add(CenterCrop())
                add(RoundedCornersTransformation(radius.dp, 0, cornerType))
            }
        }
        TYPE_RADIUS_NOT_CROP -> {
            list.add(RoundedCornersTransformation(radius.dp, 0, cornerType))
        }
        TYPE_BLURRED -> list.add(BlurTransformation(blurred, scale))
        TYPE_BLURRED_RADIUS -> {
            list.apply {
                add(CenterCrop())
                add(RoundedCornersTransformation(radius.dp, 0, cornerType))
                add(BlurTransformation(blurred, scale))
            }
        }
    }
    return if (list.size > 0)
        MultiTransformation(list)
    else {
        null
    }
}
```
#### 4. 建议用函数回调取代接口回调（可使用类型别名声明，方便使用lambda表达式）;
例如下面定义的类型别名在图片加载事件中的回调监听：
```kotlin
/**
 * Glide's image loads the callback function
 * Callback for [com.william.base_component.image.GlideLoader.load]
 */
typealias OnGlideResourceReady = (resource: Drawable?, model: Any?, target: Target<Drawable?>?, dataSource: DataSource?, isFirstResource: Boolean) -> Unit

/**
 * Glide's image load failed callback function
 * Callback for [com.william.base_component.image.GlideLoader.load]
 */
typealias OnGlideLoadFailed = (e: GlideException?, model: Any?, target: Target<Drawable?>?, isFirstResource: Boolean) -> Unit
```
调用处：
```kotlin
loadImage(imageView, url, imageView,
             onResourceReady = { resource: Drawable?, model: Any?, target: Target<Drawable?>?,
                                dataSource: DataSource?, isFirstResource: Boolean ->
                // do sth
             },
             onLoadFailed = { e: GlideException?, model: Any?,
                             target: Target<Drawable?>?, isFirstResource: Boolean ->
                // do sth
             }
          )
```

#### 5. 少写重载方法，多用具名函数，函数在重写的时候注意形参的参数名要一致；
还是借用图片加载函数举例，函数中需要的参数才对其赋值，不需要的就可以不写，极大的减少了java中方法重载的代码，使用灵活。
#### 6. 只读变量可以使用标准委托的方式实例化，也可以自定义属性委托实现；
例如下面的访问服务器的实例用标准委托方式实例化
```kotlin
val service: ApiService by lazy {
    createService(ApiService::class.java)
}
```
#### 7. 对于对象的调用可以巧妙的利用run，let，apply等作用域函数；
```kotlin
private fun getOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
    builder.run {
        addInterceptor(httpLoggingInterceptor)
        addInterceptor(HeaderConfigInterceptor())
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    }
    return builder.build()
}

fun Context.toast(content: String?) {
    content?.let {
        Toast.makeText(BaseApp.instance, it, Toast.LENGTH_SHORT).show()
    }
}
```
#### 8. 对于数据模型可以按照页面为单位进行归类，比如主页的请求参数和返回数据可以分别放在HomeReq.kt和HomeResp.kt中，可减少文件数量；
这个比较好理解，就是将网络请求参数和返回数据类都写在一个kt文件中，以页面为单位，可以大幅减少之前的java文件数量，便于维护。