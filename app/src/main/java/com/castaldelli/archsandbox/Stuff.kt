package com.castaldelli.archsandbox

class Stuff {

    class MyWallet : Wallet{
        private var value : Int = 0

        @Synchronized override fun getValue() = this.value
        @Synchronized override fun add(valueToAdd : Int) {
            this.value = this.value + valueToAdd
        }
        @Synchronized override fun subtract(valueToSubtract : Int) {
            this.value = this.value - valueToSubtract
        }
    }

    @Synchronized fun createWallet(): Wallet = MyWallet()


}