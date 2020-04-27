# TestKotlin 
# This is a learning Demo of Kotlin, and I will update the code of Kotlin on a regular basis.
## 委托
- 

## Nothing
- 类属参数为 Nothing 的类型会作为原始类型提供给 Java。原始类型在 Java 中很少使用，应避免使用。
     
## 记录异常
会抛出受检异常的函数应使用 @Throws 来记录这些异常。运行时异常应记录在 KDoc 中。
请注意函数委托给的 API，因为它们可能会抛出 Kotlin 本来会以静默方式允许传播的受检异常。