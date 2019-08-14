package com.castaldelli.archsandbox

interface Wallet {
    fun getValue(): Int // initial value should be 0
    fun add(valueToAdd: Int)
    fun subtract(valueToSubtract: Int)
}