import ktcoin.*

fun main() {
    // Create a blockchain and some wallets
    val blockChain = BlockChain()
    val wallet1 = Wallet.create(blockChain)
    val wallet2 = Wallet.create(blockChain)

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")

    // Give 100 credits to Wallet 1
    val tx1 = Transaction(sender = wallet1.publicKey, recipient = wallet1.publicKey, amount = 100)
    tx1.sign(wallet1.privateKey)
    tx1.outputs.add(TransactionOutput(recipient = wallet1.publicKey, amount = 100, transactionHash = tx1.hash))

    // Add the transaction to a new block and add it to the blockchain
    var genesisBlock = Block(previousHash = "0")
    genesisBlock.addTransaction(tx1)
    genesisBlock = blockChain.add(genesisBlock)

    println("Blockchain isValid: ${blockChain.isValid()}")

    // Transfer some credit from wallet 1 to wallet 2
    val transferTx = wallet1.sendFundsTo(recipient = wallet2.publicKey, amountToSend = 50)
    val secondBlock = Block(previousHash = genesisBlock.hash)
    secondBlock.addTransaction(transferTx)
    blockChain.add(secondBlock)

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")
    println("Blockchain isValid: ${blockChain.isValid()}")
}
