package ktcoin

import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey

data class Wallet(val publicKey: PublicKey, val privateKey: PrivateKey, val blockChain: BlockChain) {
    companion object {
        fun create(blockChain: BlockChain): Wallet {
            val generator = KeyPairGenerator.getInstance("RSA")
            generator.initialize(2048)
            val keyPair = generator.genKeyPair()

            return Wallet(keyPair.public, keyPair.private, blockChain)
        }
    }

    val balance: Int
        get() = getMyTransactions().sumBy { it.amount }

    private fun getMyTransactions(): Collection<TransactionOutput> =
        blockChain.utxo.filterValues { it.isMine(publicKey) }.values

    fun sendFundsTo(recipient: PublicKey, amountToSend: Int): Transaction {
        if (amountToSend > balance) {
            throw IllegalArgumentException("Insufficient funds")
        }

        val tx = Transaction(sender = publicKey, recipient = recipient, amount = amountToSend)
        tx.outputs.add(TransactionOutput(recipient = recipient, amount = amountToSend, transactionHash = tx.hash))

        var collectedAmount = 0
        for (myTx in getMyTransactions()) {
            collectedAmount += myTx.amount
            tx.inputs.add(myTx)

            if (collectedAmount > amountToSend) {
                val difference = collectedAmount - amountToSend
                tx.outputs.add(TransactionOutput(recipient = publicKey, amount = difference, transactionHash = tx.hash))
                break
            } else if (collectedAmount == amountToSend) {
                break
            }
        }

        return tx.sign(privateKey)
    }
}
