package com.bignerdranch.nyethack

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("IronFoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")

val patronGold = mutableMapOf<String, Double>()


fun main(args: Array<String>) {
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }


}

private fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price"
    println(message)
    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "$patronName exclaims ${toDragonSpeak("Ah, DELICIOUS, $name!")}"
    } else {
        "$patronName says: Thanks for the $name."
    }

    println(phrase)


}

private fun toDragonSpeak(phrase: String) = phrase.replace(Regex("[aeiou]".toUpperCase())) {
    when (it.value.toLowerCase()) {
        "a" -> "4"
        "e" -> "3"
        "i" -> "1"
        "o" -> "0"
        "u" -> "|_|"
        else -> it.value
    }

}