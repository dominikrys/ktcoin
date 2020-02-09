package ktcoin

fun main(args: Array<String>) {
    val genesisBlock = Block("0", "First")
    val secondBlock = Block(genesisBlock.hash, "Second")
    val thirdBlock = Block(secondBlock.hash, "Third")

    val blockchain = Blockchain()

    println(blockchain.add(genesisBlock))
    println(blockchain.isValid())
    println(blockchain.add(secondBlock))
    println(blockchain.isValid())
    println(blockchain.add(thirdBlock))
    println(blockchain.isValid())
}
