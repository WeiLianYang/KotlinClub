/**
 * main
 * @author William
 */
fun main(args: Array<String>) {
    println("hello kotlin")

    val result: Boolean? = null
    (result == true).apply {
        println(this)
    }
    println(result)
}