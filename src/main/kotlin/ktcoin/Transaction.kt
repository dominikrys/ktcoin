package ktcoin

import java.security.PrivateKey
import java.security.PublicKey

data class Transaction(
    val sender: PublicKey,
    val recipient: PublicKey,
    val amount: Int,
    var hash: String = "",
    val inputs: MutableList<TransactionOutput> = mutableListOf(),
    val outputs: MutableList<TransactionOutput> = mutableListOf()
) {
    private var signature: ByteArray = ByteArray(0)

    init {
        hash = "${sender.encodeToString()}${recipient.encodeToString()}$amount$salt".hash()
    }

    companion object {
        var salt: Long = 0
            get() = field++
    }

    fun sign(privateKey: PrivateKey): Transaction {
        signature = "${sender.encodeToString()}${recipient.encodeToString()}$amount".sign(privateKey)
        return this
    }

    fun isSignatureValid(): Boolean {
        return "${sender.encodeToString()}${recipient.encodeToString()}$amount".verifySignature(sender, signature)
    }
}
