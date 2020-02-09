package ktcoin

fun main(args: Array<String>) {
    val genesisBlock = Block("0", "First")
    val secondBlock = Block(genesisBlock.hash, "Second")
    val thirdBlock = Block(genesisBlock.hash, "Third")

    println(genesisBlock)
    println(secondBlock)
    println(thirdBlock)
}