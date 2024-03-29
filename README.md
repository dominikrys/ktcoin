# KtCoin

[![Build Status](https://img.shields.io/travis/dominikrys/ktcoin/master?style=flat-square)](https://travis-ci.com/dominikrys/ktcoin)

Blockchain implementation in Kotlin featuring wallets, transactions and a rudimentary proof-of-work algorithm.

The goal of this small project was to learn about blockchain internals and pick up some Kotlin in the process.

## Demo

[![asciicast](https://asciinema.org/a/GlrKNI0uXiDEvVqn1MY2oQMem.svg)](https://asciinema.org/a/GlrKNI0uXiDEvVqn1MY2oQMem)

## Overview

When [`Main.kt`](./src/main/kotlin/Main.kt) is ran:

1. A blockchain and two wallets are created

2. Some credit is given to one wallet in a transaction and the transaction gets signed with the wallet's private key

3. The transaction is added to a new block which gets mined using a rudimentary proof-of-work algorithm and added to the blockchain.

4. Another transaction takes place, transferring some credit from one wallet to the other. The process is similar to above, however the second block gets linked to the first block by storing the first block's hash.

## How to Run

Make sure you have JDK 11 installed. Then, run:

```bash
./gradlew run
```

Alternatively, use Docker:

```bash
docker build .
```
