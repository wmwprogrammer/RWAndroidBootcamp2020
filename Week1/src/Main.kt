
class Card(val pip: String, val suit: Char) {

    override fun toString(): String {
        return "${pip}${suit}"
    }
}

fun main() {
    val suits = setOf('\u2660', '\u2665', '\u2666', '\u2663')
    val pips = setOf(
        "2", "3", "4", "5", "6", "7", "8", "8", "10",
        "J", "Q", "K", "A"
    )

    fun createDeck(): MutableSet<Card> {
        val deck = mutableSetOf<Card>()
        for (suit in suits) {
            for (pip in pips) {
                val card = Card(pip, suit)
                deck.add(card)
            }
        }
        return deck
    }

    // add check for too large a number of cards, or a negative value
    fun dealHand(deck: MutableSet<Card>, initialNumberOfCards: Int = 2): List<Card> {
        val hand = mutableListOf<Card>()
        for (index in 0 until initialNumberOfCards) {
            val card = deck.random()
            hand.add(card)
            deck.remove(card)
        }
        return hand
    }

    fun evaluateHand(hand: List<Card>): Int {
        var total = 0
        for (card in hand) {
            total += if (card.pip == "J" || card.pip == "Q" || card.pip == "K") {
                10
            } else if (card.pip == "A") {
                11
            } else
                card.pip.toInt()
            }
        return total
    }

    fun printResults(total: Int, hand: List<Card>) {
        val suitOne: StringBuilder = if (hand[0].suit == '\u2665' || hand[0].suit == '\u2666') {
            StringBuilder("${27.toChar()}[31m${hand[0].suit}${27.toChar()}[0m")
        } else {
            StringBuilder("${hand[0].suit}")
        }
        val suitTwo: StringBuilder = if (hand[1].suit == '\u2665' || hand[1].suit == '\u2666') {
            StringBuilder("${27.toChar()}[31m${hand[1].suit}${27.toChar()}[0m")
        } else {
            StringBuilder("${hand[1].suit}")
        }
        println("You received the following cards:")
        println("""
            *-------*
            |${hand[0].pip}      |
            |      *-------*
            |   $suitOne  |${hand[1].pip}      |
            |      |       |
            |      |   $suitTwo   |
            *------|       |
                   |      ${hand[1].pip}|
                   *-------*
        """.trimIndent())
        println ("For a total of: ${total}")

        if (total == 21) println("BLACKJACK!!! You Win!!")
        if (total >21) println("You Lose!")
    }

    val deck = createDeck()
    val hand = dealHand(deck, 2)
    val total = evaluateHand(hand)

    printResults(total, hand)
}